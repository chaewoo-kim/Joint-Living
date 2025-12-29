package com.chaewookim.accountbookformoms.domain.transaction.entity;

import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.TransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import com.chaewookim.accountbookformoms.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long assetId;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum type;

    @Column(nullable = false)
    private Boolean isFixed;

    private LocalDateTime repeatDate; // 고정 지출, 고정 수입일 경우

    public static Transaction from(TransactionRequest request, Long userId) {
        return Transaction.builder()
                .title(request.title())
                .memo(request.memo())
                .userId(userId)
                .assetId(request.assetId())
                .categoryId(request.categoryId())
                .amount(request.amount())
                .type(request.type())
                .isFixed(request.isFix())
                .repeatDate(request.repeatDate())
                .build();
    }

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
                transaction.isFixed,
                transaction.getRepeatDate()
        );
    }

    public static List<TransactionResponse> from(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::from)
                .toList();
    }

    public void updateTitle(@NotBlank String title) {
        if (title != null) this.title = title;
    }

    public void updateMemo(@NotBlank String memo) {
        if (memo != null) this.memo = memo;
    }

    public void updateAsset(@NotNull Long assetId) {
        if (assetId != null) this.assetId = assetId;
    }

    public void updateAmount(@NotNull BigDecimal amount) {
        if (amount != null) this.amount = amount;
    }
}
