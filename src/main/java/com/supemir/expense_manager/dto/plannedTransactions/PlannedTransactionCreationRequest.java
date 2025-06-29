package com.supemir.expense_manager.dto.plannedTransactions;

import com.supemir.expense_manager.entity.enums.PlannedTransactionDaysOfWeek;
import com.supemir.expense_manager.entity.enums.PlannedTransactionFrequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class PlannedTransactionCreationRequest {
    @Positive
    private Long userId;

    @Positive
    private Double amount;
    private String description;
    private PlannedTransactionFrequency frequency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private byte dayOfWeek;
    private byte dayOfMonth;

    private PlannedTransactionDaysOfWeek daysOfWeek;

}
