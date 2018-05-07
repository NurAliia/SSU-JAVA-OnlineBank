package com.example.demo.service.impl;

import com.example.demo.model.BankAccount;
import com.example.demo.model.BankAccountOperation;
import com.example.demo.repository.BankAccountOperationRepository;
import com.example.demo.service.BankAccountOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BankAccountOperationServiceImpl implements BankAccountOperationService {

    @Autowired
    private BankAccountOperationRepository bankAccountOperationRepository;

    @Override
    public void saveNewBankAccountOperation(BankAccount from, BankAccount to, Long sum, String marker) {
        BankAccountOperation operation = new BankAccountOperation();
        operation.setDate(new Date());
        operation.setSum(sum);
        operation.setSource(from);
        operation.setTarget(to.getId());
        operation.setMarker(marker);
        bankAccountOperationRepository.save(operation);
    }

    @Override
    public List<BankAccountOperation> getAllBankAccountOperations() {
        List<BankAccountOperation> bankAccountOperations=null;
        for (BankAccountOperation operation: bankAccountOperationRepository.findAll()){
            bankAccountOperations.add(operation);
        }
        return bankAccountOperations;
    }

    @Override
    public BankAccountOperation getBankAccountOperationById(Long id) {
        return bankAccountOperationRepository.findById(id).get();
    }

}
