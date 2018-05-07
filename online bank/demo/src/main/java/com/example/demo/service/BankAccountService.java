package com.example.demo.service;

import com.example.demo.model.BankAccount;

public interface BankAccountService {

    Long saveNewBankAccount(Long value);

    BankAccount getBankAccountById(Long id);

    void updateBankAccount(BankAccount account);
}
