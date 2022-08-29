package com.example.debtorbot_backend.repository;

import com.example.debtorbot_backend.entity.DebtUser;
import com.example.debtorbot_backend.entity.Debtor;
import com.example.debtorbot_backend.projection.DebtorMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DebtUserRepository extends JpaRepository<DebtUser, UUID> {

    Optional<DebtUser> findByChatId(String chatId);


    @Query(value = "select du.full_name         as debtorName,\n" +
            "       du.chat_id           as chatId,\n" +
            "       coalesce(case\n" +
            "                    when du.selected_language = 'uz' then 'Tolagan qarzingiz : '\n" +
            "                    else 'Долг, который вы заплатили : ' end ||\n" +
            "                to_char(sum(d2.debtor_debt) - sum(d2.left_over), '9 999 999 999 999 999 999') ||\n" +
            "                chr(10) ||\n" +
            "                case when du.selected_language = 'uz' then 'Qarzingiz : ' else 'Ваш долг : ' end ||\n" +
            "                to_char(sum(d2.debtor_debt), '9 999 999 999 999 999 999'),\n" +
            "                case\n" +
            "                    when du.selected_language = 'uz' then 'Tolagan qarzingiz : '\n" +
            "                    else 'Долг, который вы заплатили : ' end || 0 || chr(10) ||\n" +
            "                case when du.selected_language = 'uz' then 'Qarzingiz : ' else 'Ваш долг : ' end || 0)  as debt,\n" +
            "       store_name           as storeName,\n" +
            "       u.full_name          as fullName,\n" +
            "       u.phone_number       as phoneNumber,\n" +
            "       du.selected_language as lang,\n" +
            "       'https://www.google.com/maps/place/' || s.latitude || ',' || s.longitude || '/@' || s.latitude || ',' ||\n" +
            "       s.longitude ||\n" +
            "       ',12z/data=!3m1!1e3' as storeLocation\n" +
            "from debt_user du\n" +
            "         inner join debtor d on du.phone_number = d.phone_number\n" +
            "         inner join debt d2 on d.id = d2.debtor_id\n" +
            "         inner join store s on s.id = d2.store_id\n" +
            "         inner join users u on s.id = u.store_id\n" +
            "where d2.active is true\n" +
            "  and d.phone_number = :phone\n" +
            "group by du.chat_id, du.full_name, s.store_name, u.full_name, u.phone_number, s.latitude, s.longitude,\n" +
            "         du.selected_language", nativeQuery = true)
    List<DebtorMessage> sendMessageToDebtors(String phone);


    @Query(value = "select d2.phone_number\n" +
            "from debt d\n" +
            "         inner join debtor d2 on d.debtor_id = d2.id\n" +
            "where d.created_at + interval '1 day' * :dayCount <= current_date group by d2.phone_number",nativeQuery = true)
    List<String> getDebTor(Integer dayCount);




}
