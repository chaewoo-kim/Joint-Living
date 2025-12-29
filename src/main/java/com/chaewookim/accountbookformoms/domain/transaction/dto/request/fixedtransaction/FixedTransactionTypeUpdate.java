package com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction;

import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotNull;

public record FixedTransactionTypeUpdate(
        @NotNull
        TransactionTypeEnum type
) {
}
