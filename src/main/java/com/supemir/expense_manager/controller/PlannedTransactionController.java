package com.supemir.expense_manager.controller;

import com.supemir.expense_manager.dto.plannedTransactions.PlannedTransactionCreationRequest;
import com.supemir.expense_manager.dto.plannedTransactions.PlannedTransactionMetaResponse;
import com.supemir.expense_manager.mapper.PlannedTransactionMapper;
import com.supemir.expense_manager.entity.PlannedTransactionEntity;
import com.supemir.expense_manager.security.CustomUserDetails;
import com.supemir.expense_manager.service.PlannedTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planned-transactions")
public class PlannedTransactionController {

    @Autowired
    private PlannedTransactionMapper plannedTransactionMapper;

    @Autowired
    private PlannedTransactionService plannedTransactionService;

    @GetMapping("/mine")
    public ResponseEntity<List<?>> getMyPlannedTransactions(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        List<PlannedTransactionEntity> plannedTransactions = plannedTransactionService.getTransactionsByUserId(userDetails.getUserEntity().getId());

        return ResponseEntity.ok(
            plannedTransactionMapper.toMetaResponse(plannedTransactions)
        );
    }


    @PostMapping("/me/add")
    public ResponseEntity<?> addPlannedTransaction(
            Authentication authentication,
            @Valid @RequestBody PlannedTransactionCreationRequest plannedTransactionCreationRequest
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        System.out.println(plannedTransactionCreationRequest.getUserId());
        PlannedTransactionEntity plannedTransactionEntity = plannedTransactionService.addPlannedTransaction(
                plannedTransactionMapper.toEntity(plannedTransactionCreationRequest)
        );
        return ResponseEntity.ok(plannedTransactionMapper.toResponse(plannedTransactionEntity));
    }

    @PutMapping("/me/update/{id}")
    public ResponseEntity<?> updatePlannedTransaction(
            Authentication authentication,
            @PathVariable Long id,
            @Valid @RequestBody PlannedTransactionCreationRequest plannedTransactionCreationRequest
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        PlannedTransactionEntity plannedTransactionEntity = plannedTransactionService.updatePlannedTransaction(
                id,
                userDetails.getUserEntity().getId(),
                plannedTransactionCreationRequest
        );
        return ResponseEntity.ok(plannedTransactionMapper.toResponse(plannedTransactionEntity));
    }

    @DeleteMapping("/me/delete/{id}")
    public ResponseEntity<?> deletePlannedTransaction(
            Authentication authentication,
            @PathVariable Long id
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        PlannedTransactionEntity plannedTransactionEntity = plannedTransactionService.delete(id, userDetails.getUserEntity().getId());
        return ResponseEntity.ok().build();
    }
}
