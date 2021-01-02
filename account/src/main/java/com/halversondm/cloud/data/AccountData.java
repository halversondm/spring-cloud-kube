package com.halversondm.cloud.data;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountData extends CrudRepository<Account, Integer> {

    Optional<Account> findById(Integer id);

    List<Account> findByCustomerId(Integer customerId);

    @Query("FROM ACCOUNT")
    List<Account> findAccounts();

}
