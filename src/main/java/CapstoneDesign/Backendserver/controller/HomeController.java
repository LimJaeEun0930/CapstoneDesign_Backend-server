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

    @GetMapping("register")
    public String register_get(@ModelAttribute("user") User user, Model model) {
        log.info("회원가입창 입장");
        return "register";
    }

    @PostMapping("register")
    public String register_post(@Validated @ModelAttribute User user) {
        log.info("{}", user.getName());
        log.info("{}", user.getBirth());
        userService.join(user);
        return "login";
    }

    @GetMapping("register/idDuplicateCheck")
    @ResponseBody
    public ResponseEntity<String> register_idDuplicateCheck(@RequestParam String id) {
        log.info("id중복확인 실행됨.");
        boolean duplicated = userService.validateDuplicateMember(id);
        log.info("duplicated:{}", duplicated);
        if (duplicated) {
            return new ResponseEntity<>("duplicated", HttpStatus.BAD_REQUEST);
        } else return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("register/emailAuthorization")
    @ResponseBody
    public HttpStatus register_emailAuthorization(@RequestParam String email) {
        log.info("이메일 인증 실행...{}", email);
        mailService.mailAuthorization(email);
        return HttpStatus.OK;
    }
}
