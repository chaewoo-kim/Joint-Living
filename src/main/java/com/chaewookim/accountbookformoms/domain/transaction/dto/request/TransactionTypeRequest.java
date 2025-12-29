package com.chaewookim.accountbookformoms.domain.transaction.dto.request;

import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotBlank;

public record TransactionTypeRequest(
        @NotBlank
        TransactionTypeEnum type
) {
}
