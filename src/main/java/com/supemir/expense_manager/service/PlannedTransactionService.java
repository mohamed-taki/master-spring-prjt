package com.supemir.expense_manager.service;

import com.supemir.expense_manager.dto.plannedTransactions.PlannedTransactionCreationRequest;
import com.supemir.expense_manager.entity.PlannedTransactionEntity;
import com.supemir.expense_manager.mapper.PlannedTransactionMapper;
import com.supemir.expense_manager.repository.PlannedTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlannedTransactionService {
    @Autowired
    private PlannedTransactionRepository plannedTransactionRepository;
    @Autowired
    private PlannedTransactionMapper plannedTransactionMapper;

    public List<PlannedTransactionEntity> getTransactionsByUserId(Long userId) {

        return plannedTransactionRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No planned transactions found for user with ID: " + userId));
    }

    public PlannedTransactionEntity addPlannedTransaction(PlannedTransactionEntity plannedTransaction) {
        return plannedTransactionRepository.saveAndFlush(plannedTransaction);
    }

    public PlannedTransactionEntity updatePlannedTransaction(
            Long id,
            Long userId,
            PlannedTransactionCreationRequest plannedTransactionCreationRequest) {
        PlannedTransactionEntity plannedTransactionEntity = plannedTransactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Planned transaction not found with ID: " + id + " For User " + userId));;

        plannedTransactionMapper.updateEntityFromDto(plannedTransactionEntity, plannedTransactionCreationRequest);
        return plannedTransactionRepository.saveAndFlush(plannedTransactionEntity);
    }

    public PlannedTransactionEntity delete(long id, long userId) {
        PlannedTransactionEntity plannedTransactionEntity = plannedTransactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Planned transaction not found with ID: " + id + " For User " + userId));

        plannedTransactionRepository.delete(plannedTransactionEntity);
        return plannedTransactionEntity;
    }
}
