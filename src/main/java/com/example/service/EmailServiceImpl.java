package com.example.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.User;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailSender mailSender;

    @Override
    public void sendRegistrationEmail(User user, byte[] pdfBytes) {

        String subject = "Registration Successful - PDF Attached";

        String message =
                "Hello " + user.getAuthName() + ",\n\n" +
                "Your registration has been completed successfully.\n\n" +
                "Registration No: " + user.getRegistrationNo() + "\n" +
                "Company: " + user.getCompanyName() + "\n\n" +
                "Please find your registration PDF attached.\n\n" +
                "Regards,\nVehicle Configurator Team";

        mailSender.sendWithAttachment(
                user.getEmail(),
                subject,
                pdfBytes,
                "registration-details.pdf",
                message
        );
    }
}