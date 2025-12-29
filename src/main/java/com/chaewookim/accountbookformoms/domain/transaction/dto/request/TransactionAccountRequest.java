package com.chaewookim.accountbookformoms.domain.transaction.dto.request;

import jakarta.validation.constraints.NotNull;

public record TransactionAccountRequest(
        @NotNull
        Long assetId
) {
}
