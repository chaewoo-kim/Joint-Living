package com.chaewookim.accountbookformoms.domain.transaction.dto.response.transaction;

import com.chaewookim.accountbookformoms.domain.transaction.entity.Transaction;
import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        @NotNull
        Long id,

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
    public static TransactionResponse from(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getTitle(),
                transaction.getMemo(),
                transaction.getUserId(),
                transaction.getAssetId(),
                transaction.getCategoryId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getIsFixed(),
                transaction.getRepeatDate()
        );
    }
}
