package com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FixedTransactionRepeatDateUpdate(
        @NotNull
        LocalDateTime date
) {
}
