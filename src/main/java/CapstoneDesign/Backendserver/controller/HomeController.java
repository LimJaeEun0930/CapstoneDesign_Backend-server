package CapstoneDesign.Backendserver.controller;

import CapstoneDesign.Backendserver.domain.User;
import CapstoneDesign.Backendserver.service.MailService;
import CapstoneDesign.Backendserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class HomeController {


    private final UserService userService;
    private final MailService mailService;

    @GetMapping
    public String mainPage(Model model) {
        return "home";
    }

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }


}
