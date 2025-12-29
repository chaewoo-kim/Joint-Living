package com.chaewookim.accountbookformoms.domain.transaction.dto.request;

import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequest(
        @NotBlank
        String title,

        @NotBlank
        String memo,

        @NotNull
        Long userId,

        @NotNull
        Long assetId,

        @NotNull
        Long categoryId,

        @NotNull
        @Size(min = 1)
        BigDecimal amount,

        @NotNull
        TransactionTypeEnum type,

        @NotNull
        Boolean isFix,

        LocalDateTime repeatDate
) {
}
