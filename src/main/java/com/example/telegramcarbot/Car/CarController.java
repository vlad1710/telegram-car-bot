package com.example.telegramcarbot.Car;

import com.example.telegramcarbot.User.CustomUser;
import com.example.telegramcarbot.User.UserRole;
import com.example.telegramcarbot.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CarController {
    @Autowired
    CarService carService;
    @Autowired
    private UserService userService;

    @RequestMapping("/addcar")
    public String addCar() {
        return "addcar";
    }

    @RequestMapping(value = "/newcar", method = RequestMethod.POST)
    public String newCar(@RequestParam(required = false) String brand,
                         @RequestParam(required = false) String model,
                         @RequestParam(required = false) String dnz,
                         @RequestParam(required = false) String color,
                         @RequestParam(required = false) String year,
                         @RequestParam(required = false) String vin,
                         @RequestParam(required = false) String district,
                         @RequestParam(required = false) String inGroupDate,
                         @RequestParam(required = false) String note) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String inBaseDate = sdf.format(date);

        carService.addCar(new Car(brand, model, dnz, color, year, vin, district, inGroupDate, inBaseDate, note));

        return "redirect:/addcar?success";
    }

    @RequestMapping(value = "/getcar")
    public String getCar(@RequestParam(required = false) String dnz,
                         @RequestParam(required = false) String vin,
                         Model mvcModel) {
        mvcModel.addAttribute("cars", carService.findCarByParams(dnz, vin));
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String login = user.getUsername();
        CustomUser dbUser = userService.findByLogin(login);

        if (dbUser.getRole().equals(UserRole.ADMIN))
            mvcModel.addAttribute("role", "ADMIN");

        return "index";
    }

    @RequestMapping(value = "/changecar")
    public String changeCar(@RequestParam String cId,
                            Model model) {
        Long id = Long.parseLong(cId);
        Car car = carService.getOne(id);
        model.addAttribute("car", car);
        return "changecar";
    }

    @RequestMapping(value = "/deletecar")
    public String deleteCar(@RequestParam String cId,
                            Model model){
        Long id = Long.parseLong(cId);
        carService.deleteById(id);
        model.addAttribute("deleted", "deleted");
        return "index";
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String change(@RequestParam(required = false) String cId,
                         @RequestParam(required = false) String brand,
                         @RequestParam(required = false) String model,
                         @RequestParam(required = false) String dnz,
                         @RequestParam(required = false) String color,
                         @RequestParam(required = false) String year,
                         @RequestParam(required = false) String vin,
                         @RequestParam(required = false) String district,
                         @RequestParam(required = false) String inGroupDate,
                         @RequestParam(required = false) String note,
                         Model mvcModel) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String inBaseDate = sdf.format(date);

        carService.update(cId, brand, model, dnz, color, year, vin, district, inGroupDate, inBaseDate, note);
        Long id = Long.parseLong(cId);
        Car car = carService.getOne(id);

        mvcModel.addAttribute("car", car);

        return "changecar";
    }
}
