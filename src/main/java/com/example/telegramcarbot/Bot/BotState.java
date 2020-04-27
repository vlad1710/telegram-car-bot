package com.example.telegramcarbot.Bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public enum BotState {

    Start{
        private BotState nextState;

        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Виберiть пошук.");
        }

        @Override
        public void handleInput(BotContext context) {
            String s = context.getInput();

            if (s.equals("/dnz")){
                nextState = EnterDnz;
            }
            else if (s.equals("/vin")){
                nextState = EnterVin;
            }
            else nextState = Start;
        }

        @Override
        public BotState nextState(){
            return nextState;
        }
    },

    EnterDnz{
        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Введiть ДНЗ.");
        }

        @Override
        public BotState nextState(){
            return Approved;
        }

    },

    EnterVin{
        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Введiть VIN.");
        }

        @Override
        public BotState nextState(){
            return Approved;
        }

    },

    Approved(false){
        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Пошук завершено.");
        }

        @Override
        public BotState nextState(){
            return Start;
        }
    };

    private static BotState[] states;
    private final boolean inputNeeded;

    BotState() {
        this.inputNeeded = true;
    }

    BotState(boolean inputNeeded) {
        this.inputNeeded = inputNeeded;
    }

    public static BotState getInitialState(){
        return byId(0);
    }

    public static BotState byId(int id){
        if (states == null)
            states = BotState.values();
        return states[id];
    }

    protected void sendMessage(BotContext context, String text){
        SendMessage message = new SendMessage()
                .setChatId(context.getUser().getChatId())
                .setText(text);

        try{
            context.getBot().execute(message);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public boolean isInputNeeded(){
        return inputNeeded;
    }

    public void handleInput(BotContext context){
    }

    public abstract void enter (BotContext context);
    public abstract BotState nextState();

}