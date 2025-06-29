
package com.example.notificationservice.controller;

import com.example.notificationservice.dto.EmailRequest;
import com.example.notificationservice.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendManualEmail(@RequestBody EmailRequest request) {
        if ("create".equalsIgnoreCase(request.getType())) {
            notificationService.sendAccountCreatedEmail(request.getEmail());
            return ResponseEntity.ok("Создано: Письмо отправлено");
        } else if ("delete".equalsIgnoreCase(request.getType())) {
            notificationService.sendAccountDeletedEmail(request.getEmail());
            return ResponseEntity.ok("Удаление: Письмо отправлено");
        } else {
            return ResponseEntity.badRequest().body("Неверный тип: используйте 'create' или 'delete'");
        }
    }
}
