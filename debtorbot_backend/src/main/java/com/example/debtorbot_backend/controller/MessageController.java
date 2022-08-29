package com.example.debtorbot_backend.controller;


import com.example.debtorbot_backend.bot.DebtorBot;
import com.example.debtorbot_backend.dto.DebtorMessage;
import com.example.debtorbot_backend.entity.Attachment;
import com.example.debtorbot_backend.entity.DebtUser;
import com.example.debtorbot_backend.entity.News;
import com.example.debtorbot_backend.entity.Notification;
import com.example.debtorbot_backend.repository.AttachmentRepository;
import com.example.debtorbot_backend.repository.DebtUserRepository;
import com.example.debtorbot_backend.repository.NewsRepository;
import com.example.debtorbot_backend.repository.NotificationRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/debtors")
public class MessageController {


    private final AttachmentRepository attachmentRepository;
    private final NewsRepository newsRepository;
    private final DebtUserRepository debtUserRepository;
    private final DebtorBot debtorBot;
    private final NotificationRepository notificationRepository;

    @Autowired
    public MessageController(AttachmentRepository attachmentRepository,
                             NewsRepository newsRepository,
                             DebtUserRepository debtUserRepository,
                             DebtorBot debtorBot, NotificationRepository notificationRepository) {
        this.attachmentRepository = attachmentRepository;
        this.newsRepository = newsRepository;
        this.debtUserRepository = debtUserRepository;
        this.debtorBot = debtorBot;
        this.notificationRepository = notificationRepository;
    }


    @SneakyThrows
    @GetMapping("/img/{id}")
    public void getFile(@PathVariable UUID id, HttpServletResponse response) {
        Attachment attachment = attachmentRepository.findById(id).get();
        FileCopyUtils.copy(attachment.getFile(), response.getOutputStream());
    }


    @SneakyThrows
    @PostMapping("/debtor-image")
    public UUID saveFileMessage(@RequestParam MultipartFile file) {
        Attachment attachment = new Attachment();
        attachment.setFile(file.getBytes());
        Attachment save = attachmentRepository.save(attachment);
        return save.getId();
    }

    @PostMapping("/debtor-message")
    public News sendMessageToDebtors(@RequestBody DebtorMessage message) {
        News news = new News();
        news.setDescription(message.getDescription());
        Optional<Attachment> byId = attachmentRepository.findById(message.getAttachmentId());
        if (byId.isPresent()) {
            news.setAttachment(byId.get());
            for (DebtUser debtUser : debtUserRepository.findAll()) {
                debtorBot.sendMessageToDebtors(byId.get(), message.getDescription(), debtUser.getChatId());
            }
        }


        return newsRepository.save(news);
    }


    @PutMapping("/sendNotification")
    public Notification saveNotification(@RequestBody Notification notification) {
        Notification notification1 = notificationRepository.findAll().get(0);
        notification1.setDayCount(notification.getDayCount());
        return notificationRepository.save(notification1);
    }

    @GetMapping("/getNote")
    public Notification getNotification() {
        return notificationRepository.findAll().get(0);
    }


}
