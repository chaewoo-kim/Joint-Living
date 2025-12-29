package com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction;

import jakarta.validation.constraints.NotNull;

public record TransactionAccountRequest(
        @NotNull
        Long assetId
) {
}
