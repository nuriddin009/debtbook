package com.example.debtorbot_backend.entity;


import com.example.debtorbot_backend.utils.BotSteps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class BotUser {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;


    private String chatId;

    private String step = BotSteps.START;

    private String selected_language;

    private String fullName;

    private String phoneNumber;

    private String role;

    @OneToOne
    private Store store;

    private Integer myOffset = 5;

    private UUID debtorId;

    private Integer startMsgId;
    private Integer langBntId;
    private Integer contactBtnId;
    private Integer locationBtnId;
    private Integer msgId;
    private Integer menuBntId;
    private Integer excelMsgId;
    private Integer backMsgId;
    private Integer debtMsgId;


    @CreationTimestamp
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;
}
