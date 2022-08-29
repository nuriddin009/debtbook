package com.example.debtorbot_backend.projection;

public interface DebtorMessage {

    String getDebtorName();

    String getChatId();

    String getDebt();

    String getStoreName();

    String getFullName();

    String getPhoneNumber();

    String getStoreLocation();

    String getLang();

}
