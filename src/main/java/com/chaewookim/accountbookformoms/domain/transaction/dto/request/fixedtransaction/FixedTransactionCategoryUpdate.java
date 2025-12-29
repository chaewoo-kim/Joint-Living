package com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction;

import jakarta.validation.constraints.NotNull;

public record FixedTransactionCategoryUpdate(
        @NotNull
        Long categoryId
) {
}
