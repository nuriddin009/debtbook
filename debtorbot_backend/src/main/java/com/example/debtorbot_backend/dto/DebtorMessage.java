package com.example.debtorbot_backend.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class DebtorMessage {

    UUID attachmentId;

    String description;

}
