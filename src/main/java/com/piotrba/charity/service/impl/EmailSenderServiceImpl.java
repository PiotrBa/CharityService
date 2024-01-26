package com.piotrba.charity.service.impl;

import com.piotrba.charity.entity.Donation;
import com.piotrba.charity.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


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

    public void sendConfirmationEmail(String toMail, Donation donation) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlMsg = "<div style='border: 1px solid #ddd; padding: 10px; font-family: Arial, sans-serif; width: 60%; margin: 20px auto; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); background-color: #f9f9f9; padding: 20px; border-radius: 8px; text-align: center;'>"
                + "<h2 style='color: #444; font-size: 2rem; margin-bottom: 20px;'>Thank you for your donation, " + donation.getUser().getFirstName() + "!</h3>"
                + "<h3 style='color: black; margin-bottom: 20px;'>Details for your courier:"
                + "<ul style='list-style-type: none; padding: 0;'>"
                + "<li style='margin-bottom: 10px;'>" + donation.getStreet() + ", " + donation.getZipCode() + "</li>"
                + "<li style='margin-bottom: 10px;'>" + donation.getCity() + "</li>"
                + "<li style='margin-bottom: 10px;'>" + donation.getPickUpDate() + ", " + donation.getPickUpTime() +"</li>"
                + "<li style='margin-bottom: 10px;'>" + donation.getPickUpComment() + "</li>"
                + "<li style='color: red; margin-bottom: 20px;'>Remember to confirm on your Home Page whether the package has been received by the courier! :)"
                + "</ul></h2></div>";
        try {
            helper.setText(htmlMsg, true);
            helper.setTo(toMail);
            helper.setSubject("Your Donation Confirmation");
            helper.setFrom("balazyk.piotr@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }

    public void sendThankYouEmail(String toMail, User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String htmlMsg = "<div style='border: 1px solid #ddd; padding: 10px; font-family: Arial, sans-serif; width: 60%; margin: 20px auto; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); background-color: #f9f9f9; padding: 20px; border-radius: 8px; text-align: center;'>"
                + "<h3 style='color: #444; font-size: 2rem; margin-bottom: 20px;'>We're glad that the package has been received :)"
                + "<ul style='list-style-type: none; padding: 0;'>"
                + "<li style='margin-bottom: 10px;'>Thank you, " + user.getFirstName() + " also for helping those in need! :)</li>"
                + "<li style='margin-bottom: 10px;'>We hope this is not your last donation.</li>"
                + "<li style='margin-bottom: 10px;'>Best regards!</li>"
                + "</ul></h3></div>";
        try {
            helper.setText(htmlMsg, true);
            helper.setTo(toMail);
            helper.setSubject("Thank You!");
            helper.setFrom("balazyk.piotr@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }
}
