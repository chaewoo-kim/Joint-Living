package com.chaewookim.accountbookformoms.domain.transaction.api;

import jakarta.validation.constraints.NotBlank;

public record TransactionMemoRequest(
        @NotBlank
        String memo
) {
}
