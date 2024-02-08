package com.piotrba.charity.service.impl;

import com.piotrba.charity.entity.Contact;
import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.entity.User;
import com.piotrba.charity.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.Principal;


@Service
@AllArgsConstructor
public class EmailSenderServiceImpl{

    private JavaMailSender javaMailSender;
    private UserRepository userRepository;

    public void sendActivationEmail(String toMail, String activationLink, User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlMsg = "<div style='border: 1px solid #ddd; padding: 10px; font-family: Arial, sans-serif; width: 60%; margin: 20px auto; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); background-color: #f9f9f9; padding: 20px; border-radius: 8px; text-align: center;'>"
                + "<h2 style='color: #444; font-size: 2rem; margin-bottom: 20px;'>Hello, " + user.getFirstName() + "!"
                + "<ul style='list-style-type: none; padding: 0;'>"
                + "<li style='margin-bottom: 10px;'>To activate your account, please click on this link: " + activationLink + "</li>"
                + "</ul></h2></div>";
        try {
            helper.setText(htmlMsg, true);
            helper.setTo(toMail);
            helper.setSubject("Account activation.");
            helper.setFrom("fiolecik220@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }

    public void sendConfirmationEmail(String toMail, Donation donation) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlMsg = "<div style='border: 1px solid #ddd; padding: 10px; font-family: Arial, sans-serif; width: 60%; margin: 20px auto; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); background-color: #f9f9f9; padding: 20px; border-radius: 8px; text-align: center;'>"
                + "<h3 style='color: #444; font-size: 2rem; margin-bottom: 20px;'>Thank you for your donation, " + donation.getUser().getFirstName() + "!</h3>"
                + "<h3 style='color: black; margin-bottom: 20px;'>Details for your courier:"
                + "<ul style='list-style-type: none; padding: 0;'>"
                + "<li style='margin-bottom: 10px;'>" + donation.getStreet() + ", " + donation.getZipCode() + "</li>"
                + "<li style='margin-bottom: 10px;'>" + donation.getCity() + "</li>"
                + "<li style='margin-bottom: 10px;'>" + donation.getPickUpDate() + ", " + donation.getPickUpTime() +"</li>"
                + "<li style='margin-bottom: 10px;'>" + donation.getPickUpComment() + "</li>"
                + "<li style='color: red; margin-bottom: 20px;'>Remember to confirm on your Home Page whether the package has been received by the courier! :)"
                + "</ul></h3></div>";
        try {
            helper.setText(htmlMsg, true);
            helper.setTo(toMail);
            helper.setSubject("Your Donation Confirmation");
            helper.setFrom("fiolecik220@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }

    public void sendThankYouEmail(String toMail, User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlMsg = "<div style='border: 1px solid #ddd; padding: 10px; font-family: Arial, sans-serif; width: 60%; margin: 20px auto; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); background-color: #f9f9f9; padding: 20px; border-radius: 8px; text-align: center;'>"
                + "<h2 style='color: #444; font-size: 2rem; margin-bottom: 20px;'>We're glad that the package has been received :)"
                + "<ul style='list-style-type: none; padding: 0;'>"
                + "<li style='margin-bottom: 10px;'>Thank you, " + user.getFirstName() + " also for helping those in need! :)</li>"
                + "<li style='margin-bottom: 10px;'>We hope this is not your last donation.</li>"
                + "<li style='margin-bottom: 10px;'>Best regards!</li>"
                + "</ul></h2></div>";
        try {
            helper.setText(htmlMsg, true);
            helper.setTo(toMail);
            helper.setSubject("Thank You!");
            helper.setFrom("fiolecik220@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }

    public void sendContactEmail(Contact contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(contactForm.getEmail());
        message.setTo("fiolecik220@gmail.com");
        message.setSubject("Contact message");
        message.setText("From: " + contactForm.getName() + "\nEmail: " + contactForm.getEmail() + "\nMessage: " + contactForm.getMessage());
        javaMailSender.send(message);
    }

    public void sendLoginUserContactEmail(Contact contactForm, Principal principal) {
        SimpleMailMessage message = new SimpleMailMessage();
        User user = userRepository.getByUsername(principal.getName());
        message.setFrom(user.getEmail());
        message.setTo("fiolecik220@gmail.com");
        message.setSubject("Contact message from user: " + user.getUsername());
        message.setText("Title: " + contactForm.getTitle() + "\nMessage: " + contactForm.getMessage());
        javaMailSender.send(message);
    }

    public void sendResetPasswordEmail(String toMail, String activationLink, User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlMsg = "<div style='border: 1px solid #ddd; padding: 10px; font-family: Arial, sans-serif; width: 60%; margin: 20px auto; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); background-color: #f9f9f9; padding: 20px; border-radius: 8px; text-align: center;'>"
                + "<h2 style='color: #444; font-size: 2rem; margin-bottom: 20px;'>Hello, " + user.getFirstName() + "!"
                + "<ul style='list-style-type: none; padding: 0;'>"
                + "<li style='margin-bottom: 10px;'>To reset your password, please click on this link: " + activationLink + "</li>"
                + "</ul></h2></div>";
        try {
            helper.setText(htmlMsg, true);
            helper.setTo(toMail);
            helper.setSubject("Reset password");
            helper.setFrom("fiolecik220@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }
}
