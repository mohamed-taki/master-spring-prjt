package com.supemir.expense_manager.controller;

import com.supemir.expense_manager.dto.transactions.TransactionCreationRequest;
import com.supemir.expense_manager.dto.transactions.TransactionUpdateRequest;
import com.supemir.expense_manager.mapper.TransactionMapper;
import com.supemir.expense_manager.entity.TransactionEntity;
import com.supemir.expense_manager.security.CustomUserDetails;
import com.supemir.expense_manager.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMapper transactionMapper;


    // Get current user's transactions
    @GetMapping("/mine")
    public ResponseEntity<?> getCurrentUserTransactions(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<TransactionEntity> transactions = transactionService.getUserTransactions(userDetails.getUserEntity().getId());
        return ResponseEntity.ok(transactionMapper.toResponse(transactions));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(Authentication authentication, @Valid @RequestBody TransactionCreationRequest transactionCreationRequest) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        TransactionEntity transaction = transactionService.addTransaction(transactionCreationRequest, userDetails.getUserEntity());
        return ResponseEntity.ok(transactionMapper.toResponse(transaction));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @Valid @RequestBody TransactionUpdateRequest transactionUpdateRequest) {
        TransactionEntity transaction = transactionService.updateTransactionById(id, transactionUpdateRequest);
        return ResponseEntity.ok(transactionMapper.toResponse(transaction));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.ok().build();
    }
}
