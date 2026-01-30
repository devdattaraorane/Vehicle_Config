package com.example.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String toEmail, String userName, String password) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Registration Successful");

        message.setText(
            "Hello " + userName + ",\n\n" +
            "Your registration was successful.\n\n" +
            "Login Details:\n" +
            "Username: " + toEmail + "\n" +
            "Password: " + password + "\n\n" +
            "You can now login to the application.\n\n" +
            "Regards,\nTeam"
        );

        mailSender.send(message);

        System.out.println("EMAIL SENT TO: " + toEmail);
    }

}
