
package com.example.notificationservice.consumer;

import com.example.notificationservice.event.UserEvent;
import com.example.notificationservice.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventsConsumer {

    private final NotificationService notificationService;

    public UserEventsConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(
            topics = "user-events",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(UserEvent event) {
        notificationService.handleUserEvent(event);
    }
}