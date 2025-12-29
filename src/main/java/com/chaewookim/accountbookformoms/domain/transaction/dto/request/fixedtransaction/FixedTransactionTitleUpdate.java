package com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction;

import jakarta.validation.constraints.NotBlank;

public record FixedTransactionTitleUpdate(
        @NotBlank
        String title
) {
}
