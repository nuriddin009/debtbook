package com.example.debtorbot_backend.config;

import com.example.debtorbot_backend.entity.Notification;
import com.example.debtorbot_backend.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutoRun implements CommandLineRunner {

    @Autowired
    NotificationRepository notificationRepository;


    @Override
    public void run(String... args) throws Exception {
        List<Notification> all = notificationRepository.findAll();

        if (all.size() == 0) {
            Notification notification = new Notification();
            notification.setDayCount(30);
            notificationRepository.save(notification);
        }
    }
}
