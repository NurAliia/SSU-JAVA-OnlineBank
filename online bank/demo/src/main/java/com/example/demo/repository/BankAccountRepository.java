package com.example.demo.repository;

import com.example.demo.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by NurAliya on 30.04.2018.
 */
@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
}
