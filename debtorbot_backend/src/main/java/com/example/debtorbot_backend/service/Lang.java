package com.example.debtorbot_backend.service;


import com.example.debtorbot_backend.entity.DebtUser;
import org.telegram.telegrambots.meta.api.objects.User;

import static com.example.debtorbot_backend.utils.Variables.*;

public interface Lang {


    static String gatheringBotStart(User from) {
        return "\uD83C\uDDFA\uD83C\uDDFF Assalamu alaykum " + from.getFirstName()
                + "! Botimizga xush kelibsiz.\n" +
                "Botdan foydalanish uchun iltimos o'zingizga kerakli tilni tanlang! \n" +
                "\uD83C\uDDF7\uD83C\uDDFA Привет " + from.getFirstName() + "! Добро пожаловать в наш бот.\n" +
                "Чтобы использовать бота, выберите нужный язык! \n";
//                "\uD83C\uDDFA\uD83C\uDDF8 Hello " + from.getFirstName() + "! Welcome to our bot.\n" +
//                "To use the bot, please choose the language you want!";
    }


    static String generateContactBtnText(DebtUser user) {
        return user.getSelected_language().equals("uz") ? SEND_YOUR_CONTACT_UZ
                : user.getSelected_language().equals("ru") ? SEND_YOUR_CONTACT_RU
                : SEND_YOUR_CONTACT_ENG;
    }


    static String addStoreBtnText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? ADD_STORE_UZ
                : lang.equals("ru") ? ADD_STORE_RU
                : ADD_STORE_ENG;
    }

    static String helpText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? HELP_UZ : lang.equals("ru") ? HELP_RU : HELP_ENG;
    }

    static String settingsFromUSer(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? SETTINGS_UZ : lang.equals("ru") ? SETTINGS_RU : SETTINGS_ENG;
    }


    static String selectMenu(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? CHOOSE_MENU_UZ
                : lang.equals("ru") ? CHOOSE_MENU_RU
                : CHOOSE_MENU_ENG;
    }


    static String storesBtnText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? SEE_STORES_UZ
                : lang.equals("ru") ? SEE_STORES_RU
                : SEE_STORES_ENG;
    }

    static String seeCustomersBtnText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? CUSTOMERS_UZ : lang.equals("ru") ? CUSTOMERS_RU : CUSTOMERS_ENG;
    }

    static String enterYourFullNameRequestText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "To'liq ismingizni kiriting \uD83D\uDCDD"
                : lang.equals("ru") ? "Введите свое полное имя \uD83D\uDCDD"
                : "Enter your full name \uD83D\uDCDD";
    }

    static String enterYourRealName(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Iltimos ismingizni to'g'ri kiriting ❗️"
                : lang.equals("ru") ? "Пожалуйста, введите ваше имя правильно ❗️"
                : "Please enter your name correctly ❗️";
    }

    static String askStoreName(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Do'koningizning nomini kiriting! \uD83D\uDCDD"
                : lang.equals("ru") ? "Введите название вашего магазина! \uD83D\uDCDD"
                : "Enter the name of your store! \uD83D\uDCDD";
    }

    static String sendLocationOfStore(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Do'koningizning manzilini pastdagi tugma orqali yuboring!"
                : lang.equals("ru") ? "Отправьте адрес своего магазина с помощью кнопки ниже!" :
                "Send your store address via the button below!";
    }


    static String sendMyLocationBtnText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Do'konning manzilini jo'natish \uD83D\uDCCD"
                : lang.equals("ru") ? "Отправьте адрес магазина \uD83D\uDCCD" :
                "Send the address of the store \uD83D\uDCCD";
    }

    static String successfullyRegisteredMsg(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Tabriklaymiz siz muvaffaqiyatli ro'yxatdan o'tdingiz.\n" +
                "Botni ishlatish bo'yicha video yo'riqnoma"
                : lang.equals("ru") ? "Поздравляем, вы успешно зарегистрировались.\n" +
                "Видео инструкция как пользоваться ботом"
                : "Congratulations, you have successfully registered.\n" +
                "Video instructions on how to use the bot";
    }


    static String useBotInstruction(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qo'llanma \uD83D\uDCD1"
                : lang.equals("ru") ? "Инструкция \uD83D\uDCD1"
                : "Instruction \uD83D\uDCD1";
    }

    static String useBotInstructionText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Botdan foydalanish uchun yo'riqnoma \uD83D\uDCD1"
                : lang.equals("ru") ? "Инструкция по использованию бота \uD83D\uDCD1"
                : "Instructions for using the bot \uD83D\uDCD1";
    }

    static String sorryStoreNameDoesNotExist(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Kechirasiz, bu doʻkon nomi uchun mos nom emas.\n" +
                " Doʻkon nomi 100 belgidan oshmasligi kerak"
                : lang.equals("ru") ? "Извините, это неправильное название для названия магазина. \n" +
                "Название магазина не должно превышать 100 символов."
                : "Sorry, this isn't a proper name for a store name.\n" +
                "The name of the shop must not exceed 100 characters";
    }


    static String startWorkingBtnText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Ishni boshlash \uD83D\uDC68\u200D\uD83D\uDCBB"
                : lang.equals("ru") ? "Начиная \uD83D\uDC68\u200D\uD83D\uDCBB"
                : "Getting Started \uD83D\uDC68\u200D\uD83D\uDCBB";
    }

    // statics

    static String reportText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Hisobot :  " : lang.equals("ru") ? "Отчет :  " : "Report :  ";
    }

    static String reportTodayText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Bugun :  " : lang.equals("ru") ? "Сегодня :  " : "Today :  ";
    }

    static String reportYesterdayText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Kecha :  " : lang.equals("ru") ? "Вчерашний день :  " : "Yesterday :  ";
    }

    static String reportWeeklyText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Haftalik :  " : lang.equals("ru") ? "Еженедельно :  " : "Weekly :  ";
    }

    static String reportMonthlyText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Oylik :  " : lang.equals("ru") ? "Ежемесячно :  " : "Monthly :  ";
    }

    static String descriptionReport(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "jami to'langan qarzlar / jami berilgan qarzlar"
                : lang.equals("ru") ? "общая оплаченная задолженность / общий объем выданных кредитов"
                : "total paid debts / total loans issued";
    }

    static String seeAllDebtorsListText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qarzdorlarning to'liq ro'yxati \uD83D\uDCC3"
                : lang.equals("ru") ? "Полный список должников \uD83D\uDCC3"
                : "A complete list of debtors \uD83D\uDCC3";
    }

    static String addDebtorText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qarzlarni bosharish \uD83D\uDCB5"
                : lang.equals("ru") ? "Управление долгом \uD83D\uDCB5"
                : "Debt management \uD83D\uDCB5";
    }

    static String getReportText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Hisobot \uD83D\uDCC8"
                : lang.equals("ru") ? "Отчет \uD83D\uDCC8"
                : "Report \uD83D\uDCC8";
    }

    static String goToHomeMenu(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "\uD83C\uDFE0 Bosh sahifa"
                : lang.equals("ru") ? "\uD83C\uDFE0 Домашняя страница"
                : "\uD83C\uDFE0 Home page";
    }


    static String addDebtorNameText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qarzdorning ismini kiriting \uD83D\uDCDD"
                : lang.equals("ru") ? "Введите имя должника \uD83D\uDCDD"
                : "Enter the name of the debtor \uD83D\uDCDD";
    }

    static String cancelBtn(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Bekor qilish \uD83D\uDEAB"
                : lang.equals("ru") ? "Отмена \uD83D\uDEAB"
                : "Cancel \uD83D\uDEAB";
    }

    static String confirmText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Tasdiqlash ✅" : lang.equals("ru") ? "Подтверждение ✅" : "Confirmation ✅";
    }

    static String ediText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "avvalgi qarz : "
                : lang.equals("ru") ? "предыдущий долг : "
                : "previous debt : ";
    }

    static String happenText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "yangi qarz bo'ldi: "
                : lang.equals("ru") ? "был новый кредит:" : "there was a new loan: ";
    }

    static String errorDebtorName(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Iltimos qarzdorning ismini qaytadan kiriting!\n" +
                "100 ta dan oshmasligi kerak"
                : lang.equals("ru") ? "Пожалуйста, введите имя должника еще раз!\n" +
                "  Не более 100"
                : "Please enter the debtor's name again!\n" +
                "No more than 100";
    }

    static String askDebtorPhoneFromAdmin(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qarzdorning telefon raqamini kiriting \uD83D\uDCF2\n" +
                "Eslatma! Telefon nomer +998######### shaklda kiritilishi shart!"
                : lang.equals("ru") ? "Введите номер телефона должника \uD83D\uDCF2\n" +
                "Примечание! Номер телефона необходимо вводить в виде +998#########!"
                : "Enter the debtor's phone number \uD83D\uDCF2\n" +
                "Note! Phone number must be entered in the form +998#########!";
    }

    static String errorReqMsgPhoneNum(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Iltimos telefon raqamini to'g'ri kiriting!" :
                lang.equals("ru") ? "Пожалуйста, введите номер телефона правильно!"
                        : "Please enter the phone number correctly!";
    }


    static String addDebtToDebtor(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qarz qo'shish ➕"
                : lang.equals("ru") ? "Добавить долг ➕"
                : "Add debt ➕";
    }

    static String reduceDebtFromDebtor(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qarzni to'lash ➖"
                : lang.equals("ru") ? "Оплатить долг  ➖" : "Debt repayment ➖";
    }


    static String howMuchLoanGetFirstTime(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qancha qarz kiritmoqchisiz?"
                : lang.equals("ru") ? "Сколько вы хотите занять?"
                : "How much do you want to borrow?";
    }

    static String howMuchDebtPaid(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qancha qarzni yopmoqchisiz?"
                : lang.equals("ru") ? "Какую сумму долга вы хотите погасить?"
                : "How much debt do you want to pay off?";
    }

    static String enterTrueNumber(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Iltimos xabaringizda harf va belgilardan foydalanmang!"
                : lang.equals("ru") ? "Пожалуйста, не используйте буквы и символы в сообщении!"
                : "Please do not use letters or symbols in your message!";
    }

    static String numberVeryBig(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "O' devona! Bill Gatesda ham buncha pul yo'qku"
                : lang.equals("ru") ? "О сумасшедший! Даже у Билла Гейтса нет столько денег"
                : "Oh crazy! Even Bill Gates doesn't have that much money";
    }


    static String excelText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Excelga chiqarish \uD83D\uDCC4"
                : lang.equals("ru") ? "Экспорт в Excel \uD83D\uDCC4" : "Export to Excel \uD83D\uDCC4";
    }

    static String changeLangTxt() {
        return "\uD83C\uDDFA\uD83C\uDDFF O'zingizga kerakli tilni tanlang\n" +
                "\uD83C\uDDF7\uD83C\uDDFA Выберите нужный язык\n" +
                "\uD83C\uDDFA\uD83C\uDDF8 Choose the language you want";
    }


    static String pressStart(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Iltimos qaytadan /start buyrug'ini bosing"
                : lang.equals("ru") ? "Пожалуйста, нажмите /start еще раз"
                : "Please press /start again";
    }

    static String loanBigThanDebt(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "To'lanayotgan pul qarzdan kichik bo'lishi kerak"
                : lang.equals("ru") ? "Выплачиваемая сумма должна быть меньше суммы долга."
                : "The amount being paid must be less than the debt";
    }

    static String debtorText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qarzdorlar" : lang.equals("ru") ? "Должники" : "Debtors";
    }

    static String nameText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Ismi" : lang.equals("ru") ? "Имя" : "Name";
    }


    static String myDebtText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qarzingizni ko'rish uchun pastdagi tugmani bosing!"
                : lang.equals("ru") ? "Нажмите кнопку ниже, чтобы просмотреть свой кредит!"
                : "Click the button below to view your credit!";
    }

    static String seeDebtBntText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Qarzimni ko'rish"
                : lang.equals("ru") ? "Смотри мой долг" : "See my debt";
    }

    static String debtsText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Do'konlardan qarzlarim"
                : lang.equals("ru") ? "Мои долги из магазинов" : "My debts from shops";
    }

    static String storeNameText(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "Do'kon nomi: "
                : lang.equals("ru") ? "Название магазина:" : "Store Name:";
    }

    static String storeNameText1(String lang) {
        return lang.equals("uz") ? "Do'kon nomi: "
                : lang.equals("ru") ? "Название магазина:" : "Store Name:";
    }

    static String numberTxt(DebtUser user) {
        String lang = user.getSelected_language();
        return lang.equals("uz") ? "☎️Tel: " : lang.equals("ru") ? "☎️Тел:" : "☎️Phone: ";
    }


    static String noDebtText(DebtUser debtor) {
        String lang = debtor.getSelected_language();
        return lang.equals("uz") ? "Qarzingiz yoq"
                : lang.equals("ru") ? "У тебя нет долга" : "You have no debt";
    }

    static String storeLocationText(DebtUser debtor) {
        String lang = debtor.getSelected_language();
        return lang.equals("uz") ? "\uD83D\uDCCD Do'konning manzili"
                : lang.equals("ru") ? "\uD83D\uDCCD Адрес магазина" : "\uD83D\uDCCD Address of the store";
    }

    static String storeLocationText1(String lang) {
        return lang.equals("uz") ? "\uD83D\uDCCD Do'konning manzili"
                : lang.equals("ru") ? "\uD83D\uDCCD Адрес магазина" : "\uD83D\uDCCD Address of the store";
    }

    static String backMenuText(DebtUser debtor) {
        String lang = debtor.getSelected_language();
        return lang.equals("uz") ? "⬅️ orqaga"
                : lang.equals("ru") ? "⬅️ назад" : "⬅️ back";
    }

    static String notificationText(String lang, String name) {
        return lang.equals("uz") ? "Hurmatli " + name + "!\n" +
                "Sizda qarzdorlik mavjud. Iltimos qarzingizni vaqtida to'lang!\n"
                : lang.equals("ru") ? "Уважаемый " + name + "!\n" +
                "У вас есть долг. Пожалуйста, погасите долг вовремя!\n"
                : "Dear " + name + "!\n" +
                "You have debt. Please pay your debt on time!";
    }


    static String debtText(String lang) {
        return lang.equals("uz") ? "Qarzingiz : " : lang.equals("ru") ? "Ваш долг :" : " Your debt : ";
    }

    static String paidDebtTxt(String lang) {
        return lang.equals("uz") ? "To'lagan qarzingiz : "
                : lang.equals("ru") ? "Долг, который вы заплатили : "
                : "";
    }


}
