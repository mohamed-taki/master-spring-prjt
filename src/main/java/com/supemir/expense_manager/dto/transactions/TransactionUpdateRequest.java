package com.supemir.expense_manager.dto.transactions;

import com.supemir.expense_manager.entity.enums.TransactionType;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionUpdateRequest {
    private String description;
    @Positive(message = "Amount must be positive")
    private Double amount;
    private TransactionType type;
}
