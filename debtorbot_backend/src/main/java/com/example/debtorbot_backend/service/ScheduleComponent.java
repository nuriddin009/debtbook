package com.example.debtorbot_backend.service;


import com.example.debtorbot_backend.bot.DebtorBot;
import com.example.debtorbot_backend.entity.DebtUser;
import com.example.debtorbot_backend.entity.Debtor;
import com.example.debtorbot_backend.projection.DebtorMessage;
import com.example.debtorbot_backend.repository.DebtUserRepository;
import com.example.debtorbot_backend.repository.DebtorRepository;
import com.example.debtorbot_backend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class ScheduleComponent {


    private final DebtUserRepository debtUserRepository;
    private final DebtorBot debtorBot;
    private final NotificationRepository notificationRepository;
    private final DebtorRepository debtorRepository;


    @Scheduled(cron = "0 0 6 * * ?")
    public void sendMessageToDebtors() {


        Integer dayCount = notificationRepository.findAll().get(0).getDayCount();
        List<String> debTor = debtUserRepository.getDebTor(dayCount);

        for (int i = 0; i < debTor.size(); i++) {

            List<DebtorMessage> toDebtors = debtUserRepository.sendMessageToDebtors(debTor.get(i));

            String customText = Lang.notificationText(toDebtors.get(0).getLang(), toDebtors.get(0).getDebtorName());

            for (DebtorMessage toDebtor : toDebtors) {

                customText += Lang.storeNameText1(toDebtor.getLang()) + toDebtor.getStoreName() + " ( " + toDebtor.getFullName() + " " +
                        "" + toDebtor.getPhoneNumber() + " ) " + "\n" +
                        Lang.storeLocationText1(toDebtor.getLang()) + "\n" + toDebtor.getStoreLocation() + "\n" +
                        toDebtor.getDebt() + "\n\n*****************\n\n";

            }

            if (toDebtors.size()!=0){
                debtorBot.sendNotificationForDebt(toDebtors.get(0).getChatId(),customText);
            }

        }


    }

}
