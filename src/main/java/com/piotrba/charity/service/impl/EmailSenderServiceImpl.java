package com.piotrba.charity.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmailSenderServiceImpl{

    private JavaMailSender javaMailSender;

    public void sendActivationEmail(String toMail, String activationLink){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("balazyk.piotr@gmail.com");
        message.setTo(toMail);
        message.setSubject("Account activation.");
        message.setText("Hello!\n\n" +
                " To activate your account, please click on this link: \n" + activationLink);
        javaMailSender.send(message);
    }
}
