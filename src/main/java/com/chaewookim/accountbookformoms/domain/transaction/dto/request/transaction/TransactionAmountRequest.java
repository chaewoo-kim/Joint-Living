package com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction;

import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionAmountRequest(
        @NotNull
        BigDecimal amount,

        @NotNull
        TransactionTypeEnum type,

        @NotNull
        Long assetId
) {
}
