package com.supemir.expense_manager.dto.transactions;

import com.supemir.expense_manager.entity.enums.TransactionType;
import lombok.Data;

@Data
public class TransactionResponse {
    private Long id;
    private TransactionType type;
    private String description;
    private Double amount;

    private Long userId;
}
