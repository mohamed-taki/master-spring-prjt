package com.supemir.expense_manager.dto.plannedTransactions;

import com.supemir.expense_manager.dto.transactions.TransactionResponse;

import java.util.List;

public class PlannedTransactionWithTransactionsResponse extends PlannedTransactionMetaResponse{
    private List<TransactionResponse> transactions;
}
