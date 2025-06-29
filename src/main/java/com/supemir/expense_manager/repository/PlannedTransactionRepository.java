package com.supemir.expense_manager.repository;

import com.supemir.expense_manager.entity.PlannedTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlannedTransactionRepository extends JpaRepository<PlannedTransactionEntity, Long> {
    Optional<List<PlannedTransactionEntity>> findByUserId(Long userId);
    Optional<PlannedTransactionEntity> findByIdAndUserId(Long id, Long userId);
}
