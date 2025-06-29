package com.supemir.expense_manager.entity;

import com.supemir.expense_manager.entity.enums.PlannedTransactionDaysOfWeek;
import com.supemir.expense_manager.entity.enums.PlannedTransactionFrequency;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "planned_transaction")
public class PlannedTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String description;
    private PlannedTransactionFrequency frequency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private byte dayOfWeek;
    private byte dayOfMonth;

    @Enumerated(EnumType.STRING)
    private PlannedTransactionDaysOfWeek daysOfWeek;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @LastModifiedDate
    @LastModifiedBy
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "plannedTransactions", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<TransactionEntity> transactions = new ArrayList<>();

}
