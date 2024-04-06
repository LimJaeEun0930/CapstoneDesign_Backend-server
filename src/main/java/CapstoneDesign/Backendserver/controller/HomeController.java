package CapstoneDesign.Backendserver.controller;

import CapstoneDesign.Backendserver.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {

    @GetMapping
    public String mainPage(Model model) {
        return "home";
    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("register")
    public String register_get(@ModelAttribute("user") User user,Model model) {
        log.info("hihi");
        return "register";
    }

    @PostMapping("register")
    public String register_post(@ModelAttribute  User user) {
        log.info("{}", user.getName());
        log.info("{}", user.getBirth());
        return "home";
    }


}
