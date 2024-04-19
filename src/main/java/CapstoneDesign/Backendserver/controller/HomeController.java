package CapstoneDesign.Backendserver.controller;

import CapstoneDesign.Backendserver.domain.User;
import CapstoneDesign.Backendserver.domain.UserLogin;
import CapstoneDesign.Backendserver.service.MailService;
import CapstoneDesign.Backendserver.service.UserService;
import CapstoneDesign.Backendserver.validator.RegisterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;


@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class HomeController {


    private final UserService userService;
    private final MailService mailService;
    private final RegisterValidator registerValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(registerValidator);
    }


    @GetMapping
    public String mainPage(Model model) {
        return "home";
    }

    @GetMapping("login")
    public String loginPage(@ModelAttribute("userLogin")UserLogin userLogin) {
        return "login";
    }

    @PostMapping("login")
    public String doLogin(@Validated @ModelAttribute("userLogin") UserLogin userLogin, BindingResult bindingResult) {
        log.info("login시도");
        //registerValidator.validate(userLogin,bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/login";
        }
        log.info("login완료");
        return "home";
    }

     @GetMapping("register")
    public String register_get(@ModelAttribute("user") User user, Model model) {
        log.info("회원가입창 입장");
        return "register";
    }


}
