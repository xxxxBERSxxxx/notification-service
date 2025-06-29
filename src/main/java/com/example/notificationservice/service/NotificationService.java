
package com.example.notificationservice.service;

import com.example.notificationservice.event.UserEvent;
import com.example.notificationservice.event.UserEventType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    @Value("${app.site-url}")
    private String siteUrl;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendAccountCreatedEmail(String email) {
        String subject = "Аккаунт успешно создан";
        String text = "Здравствуйте!\n\nВаш аккаунт на сайте " + siteUrl + " был успешно создан.";
        sendEmail(email, subject, text);
    }

    public void sendAccountDeletedEmail(String email) {
        String subject = "Аккаунт удалён";
        String text = "Здравствуйте!\n\nВаш аккаунт на сайте " + siteUrl + " был удалён.";
        sendEmail(email, subject, text);
    }

    public void handleUserEvent(UserEvent event) {
        if (event.getEventType() == UserEventType.CREATE) {
            sendAccountCreatedEmail(event.getEmail());
        } else if (event.getEventType() == UserEventType.DELETE) {
            sendAccountDeletedEmail(event.getEmail());
        }
    }
}