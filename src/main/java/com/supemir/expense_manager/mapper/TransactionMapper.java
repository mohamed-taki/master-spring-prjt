package com.supemir.expense_manager.mapper;

import com.supemir.expense_manager.dto.transactions.TransactionResponse;
import com.supemir.expense_manager.dto.transactions.TransactionUpdateRequest;
import com.supemir.expense_manager.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "user.id", target = "userId")
    TransactionResponse toResponse(TransactionEntity transaction);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(@MappingTarget TransactionEntity transactionEntity, TransactionUpdateRequest transactionUpdateRequest);

    List<TransactionResponse> toResponse(List<TransactionEntity> transactions);
}
