package com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FixedTransactionAmountUpdate(
        @NotNull
        BigDecimal amount
) {
}
