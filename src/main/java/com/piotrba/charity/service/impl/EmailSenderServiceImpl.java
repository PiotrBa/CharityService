package com.piotrba.charity.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmailSenderServiceImpl{

    private JavaMailSender javaMailSender;

    public void sendConfirmationEmail(String toMail){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("*****.*******@gmail.com");
        message.setTo(toMail);
        message.setSubject("Your Donation");
        message.setText("The courier is already on the way to the provided address!");
        message.setText("Please ensure that you are at home on the date and time you have selected.");
        message.setText("Afterwards, please mark on our website that the package has been received; this is very important for us. :)");
        javaMailSender.send(message);
    }
}
