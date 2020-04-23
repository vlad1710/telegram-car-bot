package com.example.telegramcarbot.Bot;


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

    private static final Logger LOGGER = LogManager.getLogger(ChatBot.class);
    private static final String BROADCAST = "broadcast ";
    private static final String LIST_USERS = "users";

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

        ChatUser user = chatUserRepository.findChatUserByChatId(chatId);

        if (checkIfAdminCommand(user, text))
            return;

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

        state.handleInput(context);

        do{
            state = state.nextState();
            state.enter(context);
        }while (!state.isInputNeeded());

        user.setStateId(state.ordinal());
        chatUserRepository.save(user);

    }

    private boolean checkIfAdminCommand(ChatUser user, String text){
        if(user == null || !user.getAdmin())
            return false;

        if (text.startsWith(BROADCAST)){
            LOGGER.info("Admin command recieved: " + BROADCAST);

            text = text.substring(BROADCAST.length());
            broadcast(text);

            return true;
        }else if (text.equals(LIST_USERS)){
            LOGGER.info("Admin command recieved: " + LIST_USERS);

            listUsers(user);
            return true;
        }

        return false;
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

    private void listUsers(ChatUser admin){
        StringBuilder sb = new StringBuilder("All users list:\r\n");
        List<ChatUser> users = chatUserRepository.findAll();

        users.forEach(user ->
                sb.append(user.getId())
                    .append(' ')
                    .append(user.getPhone())
                    .append("\r\n")
        );

        sendMessage(admin.getChatId(), sb.toString());
    }

    private void broadcast(String text){
        List<ChatUser> users = chatUserRepository.findAll();
        users.forEach(user -> sendMessage(user.getChatId(), text));
    }
}





























