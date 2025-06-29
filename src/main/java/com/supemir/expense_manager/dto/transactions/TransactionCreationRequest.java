package com.supemir.expense_manager.dto.transactions;

import com.supemir.expense_manager.entity.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionCreationRequest {
    private String description;
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be a positive number")
    private Double amount;

    @NotNull(message = "Transaction date cannot be null")
    private TransactionType type;
}
