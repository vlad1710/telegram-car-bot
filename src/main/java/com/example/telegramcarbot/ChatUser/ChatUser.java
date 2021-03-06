package com.example.telegramcarbot.ChatUser;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ChatUser {

    @Id
    @GeneratedValue
    private Long id;

    private Long chatId;
    private Integer stateId;

    public ChatUser() {
    }

    public ChatUser(Long chatId, Integer stateId) {
        this.chatId = chatId;
        this.stateId = stateId;
    }
}
