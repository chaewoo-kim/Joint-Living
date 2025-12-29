package com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction;

import jakarta.validation.constraints.NotNull;

public record TransactionFixRequest (
        @NotNull
        Long id,

        @NotNull
        Boolean isFixed
) {
}
