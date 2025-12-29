package com.chaewookim.accountbookformoms.domain.transaction.dto.response.transaction;

import com.chaewookim.accountbookformoms.domain.transaction.entity.FixedTransaction;
import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FixedTransactionResponse(
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

        LocalDateTime repeatDate
) {
    public static FixedTransactionResponse from(FixedTransaction fixedTransaction) {
        return new FixedTransactionResponse(
                fixedTransaction.getId(),
                fixedTransaction.getTitle(),
                fixedTransaction.getMemo(),
                fixedTransaction.getUserId(),
                fixedTransaction.getAssetId(),
                fixedTransaction.getCategoryId(),
                fixedTransaction.getAmount(),
                fixedTransaction.getType(),
                fixedTransaction.getRepeatDate()
        );
    }
}
