package com.example.demo.service.impl;

import com.example.demo.model.BankAccount;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public Long saveNewBankAccount(Long value) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setValue(value);
        return bankAccountRepository.save(bankAccount).getId();
    }

    @Override
    public BankAccount getBankAccountById(Long id) {
        return bankAccountRepository.findById(id).get();
    }

    @Override
    public void updateBankAccount(BankAccount account) {
        bankAccountRepository.save(account);
    }
}
