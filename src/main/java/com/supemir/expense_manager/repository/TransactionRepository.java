package com.supemir.expense_manager.repository;

import com.supemir.expense_manager.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByUserId(Long userId);
    Optional<TransactionEntity> findByIdAndUserId(Long id, Long userId);
}
