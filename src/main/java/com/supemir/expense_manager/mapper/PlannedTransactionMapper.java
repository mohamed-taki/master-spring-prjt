package com.supemir.expense_manager.mapper;

import com.supemir.expense_manager.dto.plannedTransactions.PlannedTransactionCreationRequest;
import com.supemir.expense_manager.dto.plannedTransactions.PlannedTransactionMetaResponse;
import com.supemir.expense_manager.dto.plannedTransactions.PlannedTransactionWithUserResponse;
import com.supemir.expense_manager.entity.PlannedTransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlannedTransactionMapper {

    PlannedTransactionMetaResponse toMetaResponse(PlannedTransactionEntity x);
    List<PlannedTransactionMetaResponse> toMetaResponse(List<PlannedTransactionEntity> x);

    List<PlannedTransactionWithUserResponse> toResponse(List<PlannedTransactionEntity> x);
    PlannedTransactionWithUserResponse toResponse(PlannedTransactionEntity x);

    @Mapping(source = "userId", target = "user.id")
    PlannedTransactionEntity toEntity(PlannedTransactionCreationRequest x);

    @Mapping(target = "id", ignore = true)
    PlannedTransactionEntity updateEntityFromDto(@MappingTarget PlannedTransactionEntity e, PlannedTransactionCreationRequest pc);

}
