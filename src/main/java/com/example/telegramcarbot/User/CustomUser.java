package com.telegramcarbot.User;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class CustomUser {
    @Id
    @GeneratedValue
    private long id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    public CustomUser(String login, String password, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.role = userRole;
    }

    public CustomUser() {
    }
}