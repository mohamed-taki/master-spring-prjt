package com.supemir.expense_manager.dto.plannedTransactions;

import com.supemir.expense_manager.entity.enums.PlannedTransactionDaysOfWeek;
import com.supemir.expense_manager.entity.enums.PlannedTransactionFrequency;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PlannedTransactionMetaResponse {
    private Long id;
    private Double amount;
    private String description;
    private PlannedTransactionFrequency frequency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private byte dayOfWeek;
    private byte dayOfMonth;

    private PlannedTransactionDaysOfWeek daysOfWeek;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
