package com.example.debtorbot_backend.bot;


import com.example.debtorbot_backend.entity.Attachment;
import com.example.debtorbot_backend.entity.DebtUser;
import com.example.debtorbot_backend.entity.Debtor;
import com.example.debtorbot_backend.projection.MyDebtProjection;
import com.example.debtorbot_backend.repository.*;
import com.example.debtorbot_backend.service.BotCallBackData;
import com.example.debtorbot_backend.service.Lang;
import com.example.debtorbot_backend.utils.BotSteps;
import com.example.debtorbot_backend.utils.BotUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.*;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;


@EqualsAndHashCode(callSuper = true)
@Component
@Transactional
@RequiredArgsConstructor
@Slf4j
@Data
public class DebtorBot extends TelegramLongPollingBot {


    @Value("${spring.telegram.debtor.bot.username}")
    private String debtorDebtUsername;

    @Value("${spring.telegram.debtor.bot.token}")
    private String debtorBotToken;


    private final DebtorRepository debtorRepository;
    private final DebtUserRepository debtUserRepository;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();

            DebtUser debtor = getDebtor(chatId);


            if (message.hasText()) {


                String text = message.getText();
                if (text.equals("/start")) {
                    if (debtor.getSelected_language() != null) {
                        if (debtor.getPhoneNumber() != null) {
                            debtor.setStep(BotSteps.SEE_MY_DEBT);
                            seeMYDebt(debtor);
                            saveUserChanges(debtor);
                        } else {
                            debtor.setStep(BotSteps.SEND_CONTACT);
                            sendRequestContactToUser(debtor);
                            saveUserChanges(debtor);
                        }
                    } else {
                        gatheringLanguage(debtor, message.getFrom());
                    }
                }
            } else if (message.hasContact()) {
                Contact contact = message.getContact();
                debtor.setFullName(contact.getFirstName() + " "
                        + (contact.getLastName() != null ? contact.getLastName() : ""));
                String phoneNumber = contact.getPhoneNumber();
                debtor.setPhoneNumber(phoneNumber.startsWith("+") ? phoneNumber : "+" + phoneNumber);
                debtor.setStep(BotSteps.SEE_MY_DEBT);
                seeMYDebt(debtor);
                saveUserChanges(debtor);

                List<Debtor> byPhoneNumber = debtorRepository
                        .findByPhoneNumber(contact.getPhoneNumber().startsWith("+") ? contact.getPhoneNumber()
                                : "+" + contact.getPhoneNumber());
                if (byPhoneNumber.size() != 0) {

                    debtor.setDebtors(byPhoneNumber);
                    debtUserRepository.save(debtor);

                }


                deleteMsg(debtor, debtor.getContactBtnId());
                deleteMsg(debtor, message.getMessageId());
            }
        } else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();
            String chaId = message.getChatId().toString();
            String data = update.getCallbackQuery().getData();
            DebtUser debtor = getDebtor(chaId);


            if (debtor.getStep().equals(BotSteps.SELECT_LANG)) {
                if (data.equals("uz")) {
                    debtor.setSelected_language("uz");
                } else if (data.equals("ru")) {
                    debtor.setSelected_language("ru");
                } else {
                    debtor.setSelected_language("eng");
                }
                sendRequestContactToUser(debtor);
                debtor.setStep(BotSteps.SEND_CONTACT);
                saveUserChanges(debtor);
                deleteMsg(debtor, debtor.getLangBntId());
            } else if (debtor.getStep().equals(BotSteps.SEE_MY_DEBT)) {
                if (data.equals(BotCallBackData.MY_DEBT)) {

                    if (debtorRepository.findByPhoneNumber(debtor.getPhoneNumber()).size() != 0) {
                        Debtor debtor1 = debtorRepository.findByPhoneNumber(debtor.getPhoneNumber()).get(0);
                        List<MyDebtProjection> debts = debtorRepository.getMyDebtFromStores(debtor1.getPhoneNumber());
                        String myDebtText = Lang.debtsText(debtor) + ":\n";

                        for (int i = 0; i < debts.size(); i++) {
                            myDebtText += i + 1 + " ) " + Lang.storeNameText(debtor) + debts.get(i).getStoreName() + "\n" +
                                    Lang.numberTxt(debtor) + debts.get(i).getPhoneNumber() + "  (" + debts.get(i).getFullName() + ")" + "\n" +
                                    Lang.storeLocationText(debtor) + "   " + debts.get(i).getStoreLocation() + "\n" + debts.get(i).getDebt() + "\n\n************\n\n";
                        }

                        List<Debtor> byPhoneNumber = debtorRepository.findByPhoneNumber(debtor.getPhoneNumber().startsWith("+") ? debtor.getPhoneNumber()
                                : "+" + debtor.getPhoneNumber());
                        if (byPhoneNumber.size() != 0) {
                            debtor.setDebtors(byPhoneNumber);
                            debtUserRepository.save(debtor);
                        }

                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText(myDebtText);
                        sendMessage.setChatId(debtor.getChatId());
                        sendMessage.setReplyMarkup(BotUtils.backMenuDebtorBtn(debtor));
                        Message execute = execute(sendMessage);
                        deleteMsg(debtor, debtor.getDebtMsgId());
                        debtor.setBackMsgId(execute.getMessageId());
                        saveUserChanges(debtor);
                    } else {
                        sendText(debtor, Lang.noDebtText(debtor));
                    }


                } else if (data.equals(BotCallBackData.GO_HOME)) {
                    deleteMsg(debtor, debtor.getBackMsgId());
                    try {
                        if (debtor.getDebtMsgId() != null) {
                            deleteMsg(debtor, debtor.getMsgId());
                        }
                    } catch (Exception e) {
                    }
                    seeMYDebt(debtor);
                }
            }
        }

    }


    @SneakyThrows
    public void sendMessageToDebtors(Attachment attachment, String description, String chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        InputFile inputFile = new InputFile();
        InputStream targetStream = new ByteArrayInputStream(attachment.getFile());
        inputFile.setMedia(targetStream, attachment.getId().toString());

        sendPhoto.setPhoto(inputFile);
        sendPhoto.setCaption(description);
        sendPhoto.setChatId(chatId);
        execute(sendPhoto);
    }


    @SneakyThrows
    private void seeMYDebt(DebtUser debtor) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(debtor.getChatId());
        sendMessage.setText(Lang.myDebtText(debtor));
        sendMessage.setReplyMarkup(BotUtils.seeMyDebtButton(debtor));
        Message execute = execute(sendMessage);
        debtor.setDebtMsgId(execute.getMessageId());
        saveUserChanges(debtor);
    }


    @SneakyThrows
    private void sendRequestContactToUser(DebtUser user) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(Lang.generateContactBtnText(user));
        sendMessage.setReplyMarkup(BotUtils.generateContactButton(user));
        Message execute = execute(sendMessage);
        user.setContactBtnId(execute.getMessageId());
        saveUserChanges(user);
    }

    @SneakyThrows
    private void sendText(DebtUser user, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(text);
        Message execute = execute(sendMessage);
        user.setMsgId(execute.getMessageId());
        saveUserChanges(user);
    }

    @SneakyThrows
    public void sendNotificationForDebt(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        execute(sendMessage);
    }


    @SneakyThrows
    private void deleteMsg(DebtUser user, Integer messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(user.getChatId());
        deleteMessage.setMessageId(messageId);
        execute(deleteMessage);
    }

    @SneakyThrows
    private void gatheringLanguage(DebtUser user, User from) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(user.getChatId());
        sendMessage.setText(Lang.gatheringBotStart(from));
        sendMessage.setReplyMarkup(BotUtils.generateGatheringLanguageButtons());
        Message execute = execute(sendMessage);
        user.setLangBntId(execute.getMessageId());
        user.setStep(BotSteps.SELECT_LANG);
        saveUserChanges(user);
    }

    private void saveUserChanges(DebtUser user) {
        debtUserRepository.save(user);
    }

    public DebtUser getDebtor(String chatId) {
        Optional<DebtUser> byChatId = debtUserRepository.findByChatId(chatId);
        if (byChatId.isPresent()) {
            return byChatId.get();
        } else {
            DebtUser DebtUser = new DebtUser();
            DebtUser.setChatId(chatId);
            return debtUserRepository.save(DebtUser);
        }
    }


    @Override
    public String getBotUsername() {
        return debtorDebtUsername;
    }

    @Override
    public String getBotToken() {
        return debtorBotToken;
    }
}
