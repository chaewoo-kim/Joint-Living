package com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction;

import jakarta.validation.constraints.NotBlank;

public record TransactionMemoRequest(
        @NotBlank
        String memo
) {
}
