package com.example.telegramcarbot;

import com.example.telegramcarbot.User.CustomUser;
import com.example.telegramcarbot.User.UserRole;
import com.example.telegramcarbot.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController implements ErrorController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model){
        User user = (User)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String login = user.getUsername();
        CustomUser dbUser = userService.findByLogin(login);

        if (dbUser.getRole().equals(UserRole.ADMIN))
            model.addAttribute("role", "ADMIN");

        model.addAttribute("login", login);

        return "index";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "addcar";
    }

    @RequestMapping("/adduser")
    public String addUser(){
        return "adduser";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
