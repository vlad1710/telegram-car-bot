package com.example.telegramcarbot.Bot;

import com.example.telegramcarbot.ChatUser.ChatUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BotContext {

    private final ChatBot bot;
    private final ChatUser user;
    private final String input;

    public static BotContext of(ChatBot bot, ChatUser user, String text) {
        return new BotContext(bot, user, text);
    }

    public BotContext(ChatBot bot, ChatUser user, String input) {
        this.bot = bot;
        this.user = user;
        this.input = input;
    }
}
