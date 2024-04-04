package CapstoneDesign.Backendserver.service;

import CapstoneDesign.Backendserver.mail.MailVo;
import jakarta.mail.SendFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {


    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}") //@Value는 런타임에 변수로 주입된다.static이 있으면 안된다. null반환됨
    // https://growth-coder.tistory.com/176
    private String senderEmail;



    public void CreateMail(MailVo mailVo) {

        log.info("Sender {}", senderEmail);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailVo.getReceiver());
            message.setFrom(senderEmail);
            message.setSubject(mailVo.getTitle());
            message.setText(mailVo.getContent());

            mailSender.send(message);

    }
}
