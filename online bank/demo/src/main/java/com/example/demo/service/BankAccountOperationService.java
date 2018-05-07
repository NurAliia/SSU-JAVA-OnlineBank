package com.example.demo.service;

import com.example.demo.model.BankAccount;
import com.example.demo.model.BankAccountOperation;

import java.util.List;

public interface BankAccountOperationService {

    void saveNewBankAccountOperation(BankAccount from, BankAccount to, Long sum, String marker);

    BankAccountOperation getBankAccountOperationById(Long id);

    List<BankAccountOperation> getAllBankAccountOperations();

  /*  List<BankAccountOperation> getBankAccountOperationsBySourceBankAccount(BankAccount source);

    List<BankAccountOperation> getBankAccountOperationsByTargetBankAccount(BankAccount target);*/
}
