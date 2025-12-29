package com.chaewookim.accountbookformoms.domain.transaction.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TransactionTitleUpdate(
        @NotBlank
        String title
) {
}
