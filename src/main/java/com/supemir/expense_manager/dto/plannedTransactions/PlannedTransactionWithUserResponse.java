package com.supemir.expense_manager.dto.plannedTransactions;

import com.supemir.expense_manager.dto.users.UserResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlannedTransactionWithUserResponse extends PlannedTransactionMetaResponse{
    private UserResponse user;
}
