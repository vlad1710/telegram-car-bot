package com.example.telegramcarbot.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CustomUser {
    @Id
    @GeneratedValue
    private long id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public CustomUser(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public CustomUser() {}

}
