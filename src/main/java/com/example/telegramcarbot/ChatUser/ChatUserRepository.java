package com.example.telegramcarbot.ChatUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

    ChatUser findChatUserByChatId(long id);

}
