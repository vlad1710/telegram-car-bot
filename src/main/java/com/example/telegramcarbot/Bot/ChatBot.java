package com.example.telegramcarbot.Bot;


import com.example.telegramcarbot.Car.Car;
import com.example.telegramcarbot.Car.CarService;
import com.example.telegramcarbot.ChatUser.ChatUser;
import com.example.telegramcarbot.ChatUser.ChatUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot {

    @Autowired
    ChatUserRepository chatUserRepository;
    @Autowired
    CarService carService;

    private static final Logger LOGGER = LogManager.getLogger(ChatBot.class);

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText())
            return;

        final String text = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();
        List<Car> cars;

        ChatUser user = chatUserRepository.findChatUserByChatId(chatId);

        BotContext context;
        BotState state;

        if(user == null){
            state = BotState.getInitialState();

            user = new ChatUser(chatId, state.ordinal());
            chatUserRepository.save(user);

            context = BotContext.of(this, user, text);
            state.enter(context);

            LOGGER.info("New user registered: " + chatId);
        }else {
            context = BotContext.of(this, user, text);
            state = BotState.byId(user.getStateId());

            LOGGER.info("Update recieved for this user in state: " + state);
        }

        if (user.getStateId() == 1){
            cars = carService.findCarByDnz(text);
            StringBuilder sb = new StringBuilder();

            if(cars.isEmpty())
                sb.append("Не знайдено.");

            for (Car c : cars) {
                sb.append(c.toString());
            }

            sendMessage(chatId, sb.toString());
        }
        else if (user.getStateId() == 2){
            cars = carService.findCarByVin(text);
            StringBuilder sb = new StringBuilder();

            if(cars.isEmpty())
                sb.append("Не знайдено.");

            for (Car c : cars) {
                sb.append(c.toString());
            }
            sendMessage(chatId, sb.toString());
        }

        state.handleInput(context);

        do{
            state = state.nextState();
            state.enter(context);
        }while (!state.isInputNeeded());

        user.setStateId(state.ordinal());
        chatUserRepository.save(user);

    }

    private void sendMessage(long chatId, String text){
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        try {
            execute(message);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}