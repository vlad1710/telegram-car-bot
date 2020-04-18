package com.example.telegramcarbot;

import com.example.telegramcarbot.User.CustomUser;
import com.example.telegramcarbot.User.UserRole;
import com.example.telegramcarbot.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
    @Autowired
    private UserService userService;

    @Autowired
    private ShaPasswordEncoder passwordEncoder;

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

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(required = false) String email,
                         @RequestParam(required = false) String phone) {

        return "redirect:/";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         Model model) {
        String passHash = passwordEncoder.encodePassword(password, null);

        if ( ! userService.addUser(login, passHash)) {
            model.addAttribute("exists", true);
            model.addAttribute("login", login);
            return "addcar";
        }

        return "redirect:/";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "addcar";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }
}
