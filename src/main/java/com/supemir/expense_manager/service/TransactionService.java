package com.supemir.expense_manager.service;

import com.supemir.expense_manager.dto.transactions.TransactionCreationRequest;
import com.supemir.expense_manager.dto.transactions.TransactionUpdateRequest;
import com.supemir.expense_manager.mapper.TransactionMapper;
import com.supemir.expense_manager.entity.TransactionEntity;
import com.supemir.expense_manager.entity.UserEntity;
import com.supemir.expense_manager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    public TransactionEntity addTransaction(TransactionCreationRequest transactionCreationRequest, UserEntity userEntity) {
        TransactionEntity new_transaction = new TransactionEntity();
        new_transaction.setUser(userEntity);
        new_transaction.setType(transactionCreationRequest.getType());
        new_transaction.setDescription(transactionCreationRequest.getDescription());
        new_transaction.setAmount(transactionCreationRequest.getAmount());

        return transactionRepository.saveAndFlush(new_transaction);
    }

    public List<TransactionEntity> getUserTransactions(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public TransactionEntity updateTransactionById(Long id, TransactionUpdateRequest transactionUpdateRequest) {
        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transactionMapper.updateEntityFromDto(transaction, transactionUpdateRequest);
        transactionRepository.saveAndFlush(transaction);
        return transaction;
    }

    public void deleteTransactionById(Long id) {
        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transactionRepository.delete(transaction);
    }

    public void deleteUserTransactionById(Long id, Long userId){
        TransactionEntity transaction = transactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Transaction doesnt exist for this user"));

        transactionRepository.delete(transaction);
    }
}
