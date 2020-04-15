package com.telegramcarbot.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public CustomUser findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    public boolean addUser(String login, String passHash) {
        if (userRepository.existsByLogin(login))
            return false;


        CustomUser user = new CustomUser(login, passHash, UserRole.USER);
        userRepository.save(user);

        return true;
    }
}
