package com.example.debtorbot_backend.repository;


import com.example.debtorbot_backend.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DebtRepository extends JpaRepository<Debt, UUID> {



    @Query(value = "select (case\n" +
            "            when sum(d.debtor_debt) is null then 0 \n" +
            "            else sum(d.debtor_debt)  end) as debt\n" +
            "from debt d\n" +
            "         inner join debtor d2 on d2.id = d.debtor_id\n" +
            "         inner join store s on s.id = d.store_id\n" +
            "where d.store_id = :storeId\n" +
            "  and d2.id = :debtorId\n" +
            "  and d.active is true", nativeQuery = true)
    Long getOneDebtorsDebt(UUID storeId, UUID debtorId);

}
