package com.example.debtorbot_backend.repository;


import com.example.debtorbot_backend.entity.Debtor;
import com.example.debtorbot_backend.projection.MyDebtProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DebtorRepository extends JpaRepository<Debtor, UUID> {


    List<Debtor> findByPhoneNumber(String phoneNumber);



    @Query(value = "select s.store_name as storeName,\n" +
            "       u.full_name as fullName,\n" +
            "       u.phone_number as phoneNumber,\n" +
            "       coalesce(sum(d2.debtor_debt) - sum(d2.left_over) || ' / ' || sum(d2.debtor_debt), 0 || ' / ' || 0) as debt,\n" +
            "              'https://www.google.com/maps/place/' || s.latitude || ',' || s.longitude || '/@' || s.latitude || ',' ||\n" +
            "       s.longitude || ',12z/data=!3m1!1e3'                                                             as storeLocation\n" +
            "from debtor d\n" +
            "         inner join debtor_stores ds on d.id = ds.debtor_id\n" +
            "         inner join store s on s.id = ds.stores_id\n" +
            "         inner join users u on s.id = u.store_id\n" +
            "         inner join debt d2 on d.id = d2.debtor_id\n" +
            "where d.phone_number = :ownerPhone group by s.store_name, u.full_name,s.latitude,s.longitude,u.phone_number", nativeQuery = true)
    List<MyDebtProjection> getMyDebtFromStores(String ownerPhone);







}
