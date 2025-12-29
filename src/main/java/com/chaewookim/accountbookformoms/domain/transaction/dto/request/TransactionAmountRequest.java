package com.chaewookim.accountbookformoms.domain.transaction.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionAmountRequest(
        @NotNull
        BigDecimal amount
) {
}
