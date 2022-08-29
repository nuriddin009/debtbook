package com.example.debtorbot_backend.utils;

import com.example.debtorbot_backend.entity.DebtUser;
import com.example.debtorbot_backend.service.BotCallBackData;
import com.example.debtorbot_backend.service.Lang;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public interface BotUtils {


    static InlineKeyboardMarkup generateGatheringLanguageButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> td = new ArrayList<>();
        InlineKeyboardButton uzButton = new InlineKeyboardButton();
        uzButton.setText("\uD83C\uDDFA\uD83C\uDDFF o'z");
        uzButton.setCallbackData("uz");
        InlineKeyboardButton ruButton = new InlineKeyboardButton();
        ruButton.setText("\uD83C\uDDF7\uD83C\uDDFA ру");
        ruButton.setCallbackData("ru");
        InlineKeyboardButton engButton = new InlineKeyboardButton();
        engButton.setText("\uD83C\uDDFA\uD83C\uDDF8 eng");
        engButton.setCallbackData("eng");
        td.add(uzButton);
        td.add(ruButton);
//        td.add(engButton);
        List<List<InlineKeyboardButton>> tr = new ArrayList<>();
        tr.add(td);
        inlineKeyboardMarkup.setKeyboard(tr);
        return inlineKeyboardMarkup;
    }


    static ReplyKeyboardMarkup generateContactButton(DebtUser user) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(Lang.generateContactBtnText(user));
        keyboardButton.setRequestContact(true);
        keyboardRow.add(keyboardButton);
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(keyboardRow);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }


    static ReplyKeyboardMarkup generateMenuButton(DebtUser user) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> tr = new ArrayList<>();
        KeyboardRow td = new KeyboardRow();
        KeyboardButton btn1 = new KeyboardButton();
        btn1.setText(Lang.addStoreBtnText(user)); // add store
        td.add(btn1);
        tr.add(td);
        replyKeyboardMarkup.setKeyboard(tr);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }


    static ReplyKeyboardMarkup generateSuperAdminMainMenuButtons(DebtUser user) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();

        // 1-row
        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton button1 = new KeyboardButton();
        button1.setText(Lang.storesBtnText(user));

        KeyboardButton button2 = new KeyboardButton();
        button2.setText(Lang.seeCustomersBtnText(user));

        // 2-row
        KeyboardRow row2 = new KeyboardRow();

//        KeyboardButton button3 = new KeyboardButton();
//        button3.setText(Lang.addStoreBtnText(user));

        KeyboardButton button4 = new KeyboardButton();
        button4.setText(Lang.settingsFromUSer(user));


        row1.add(button1);
        row1.add(button2);

//        row2.add(button3);
        row2.add(button4);

        rows.add(row1);
        rows.add(row2);
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }


    static ReplyKeyboardMarkup generateLocationButton(DebtUser user) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(Lang.sendMyLocationBtnText(user));
        keyboardButton.setRequestLocation(true);
        keyboardRow.add(keyboardButton);
        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(keyboardRow);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }

    static InlineKeyboardMarkup generateSuccessfullyRegisteredButton(DebtUser user) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(Lang.useBotInstruction(user));
        button.setUrl("https://youtu.be/CymRDgsfBMM");
        button.setCallbackData("Bla bla bla");

        InlineKeyboardButton startWorking = new InlineKeyboardButton();
        startWorking.setText(Lang.startWorkingBtnText(user));
        startWorking.setCallbackData(BotSteps.START_WORKING);


        row.add(button);
        row.add(startWorking);
        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    static InlineKeyboardMarkup seeAllDebtorsList(DebtUser user) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(Lang.seeAllDebtorsListText(user));
        button.setCallbackData(BotCallBackData.LIST_DEBTORS);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setCallbackData(BotCallBackData.GO_HOME);
        button1.setText(Lang.goToHomeMenu(user));

        row.add(button);
        row1.add(button1);
        rows.add(row);
        rows.add(row1);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    static InlineKeyboardMarkup goToHomeButton(DebtUser user) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setCallbackData(BotCallBackData.GO_HOME);
        button1.setText(Lang.goToHomeMenu(user));
        row1.add(button1);
        rows.add(row1);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    static InlineKeyboardMarkup cancelButton(DebtUser user) {
        InlineKeyboardMarkup inlineKeyboardMarkup1 = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(BotCallBackData.GO_HOME);
        button.setText(Lang.cancelBtn(user));
        row.add(button);
        rowList.add(row);
        inlineKeyboardMarkup1.setKeyboard(rowList);
        return inlineKeyboardMarkup1;
    }


    static InlineKeyboardMarkup generateMainMenuButtons(DebtUser user) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(Lang.getReportText(user));
        button.setCallbackData(BotCallBackData.GET_REPORT);

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(Lang.addDebtorText(user));
        button1.setCallbackData(BotCallBackData.ADD_DEBTOR);


        List<InlineKeyboardButton> rowHelp = new ArrayList<>();
        InlineKeyboardButton buttonHelp = new InlineKeyboardButton();
        buttonHelp.setText(Lang.useBotInstruction(user));
        buttonHelp.setUrl("https://youtu.be/CymRDgsfBMM");
        buttonHelp.setCallbackData("Bla bla bla");


        row.add(button);
        row.add(button1);
        rowHelp.add(buttonHelp);
        rows.add(row);
        rows.add(rowHelp);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }


    static InlineKeyboardMarkup addDecDebtButtons(DebtUser user) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setCallbackData(BotCallBackData.ADD_DEBT);
        button1.setText(Lang.addDebtToDebtor(user));

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setCallbackData(BotCallBackData.REDUCE_DEBT);
        button2.setText(Lang.reduceDebtFromDebtor(user));


        row1.add(button1);
        row1.add(button2);
        rows.add(row1);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    static InlineKeyboardMarkup addDebtButtons(DebtUser user) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setCallbackData(BotCallBackData.ADD_DEBT);
        button1.setText(Lang.addDebtToDebtor(user));

        row1.add(button1);
        rows.add(row1);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }


    static InlineKeyboardMarkup generateExcelButton(DebtUser user) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(Lang.excelText(user));
        button.setCallbackData(BotCallBackData.EXCEL);

        List<InlineKeyboardButton> row1 = new ArrayList<>();

        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(Lang.goToHomeMenu(user));
        button1.setCallbackData(BotCallBackData.GO_HOME);


        row.add(button);
        row1.add(button1);
        rows.add(row);
        rows.add(row1);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    static InlineKeyboardMarkup generatePaginationButton(DebtUser user) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row01 = new ArrayList<>();
        InlineKeyboardButton prev = new InlineKeyboardButton();
        prev.setText("⏪");
        prev.setCallbackData(BotCallBackData.PREVIOUS);
        InlineKeyboardButton nextBtn = new InlineKeyboardButton();
        nextBtn.setText("⏩");
        nextBtn.setCallbackData(BotCallBackData.NEXT);


        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(Lang.excelText(user));
        button.setCallbackData(BotCallBackData.EXCEL);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(Lang.goToHomeMenu(user));
        button1.setCallbackData(BotCallBackData.GO_HOME);

        row01.add(prev);
        row01.add(nextBtn);

        row.add(button);
        row1.add(button1);
        rows.add(row01);
        rows.add(row);
        rows.add(row1);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }


    static InlineKeyboardMarkup generatePaginationNextButton(DebtUser user) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row01 = new ArrayList<>();
        InlineKeyboardButton nextBtn = new InlineKeyboardButton();
        nextBtn.setText("⏩");
        nextBtn.setCallbackData(BotCallBackData.NEXT);

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(Lang.excelText(user));
        button.setCallbackData(BotCallBackData.EXCEL);

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText(Lang.goToHomeMenu(user));
        button1.setCallbackData(BotCallBackData.GO_HOME);

        row01.add(nextBtn);

        row.add(button);
        row1.add(button1);
        rows.add(row01);
        rows.add(row);
        rows.add(row1);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }




    static InlineKeyboardMarkup confirmOrCancelButtons(DebtUser user) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton confirmBnt = new InlineKeyboardButton();
        confirmBnt.setText(Lang.confirmText(user));
        confirmBnt.setCallbackData(BotCallBackData.CONFIRM);


        InlineKeyboardButton cancelBnt = new InlineKeyboardButton();
        cancelBnt.setText(Lang.cancelBtn(user));
        cancelBnt.setCallbackData(BotCallBackData.CANCEL);


        row.add(confirmBnt);
        row.add(cancelBnt);
        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }


    static InlineKeyboardMarkup seeMyDebtButton(DebtUser debtor) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(Lang.seeDebtBntText(debtor));
        button.setCallbackData(BotCallBackData.MY_DEBT);

        row.add(button);
        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }

    static InlineKeyboardMarkup backMenuDebtorBtn(DebtUser debtor) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(Lang.backMenuText(debtor));
        button.setCallbackData(BotCallBackData.GO_HOME);

        row.add(button);
        rows.add(row);
        inlineKeyboardMarkup.setKeyboard(rows);
        return inlineKeyboardMarkup;
    }


}
