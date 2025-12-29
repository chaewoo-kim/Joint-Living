package com.chaewookim.accountbookformoms.domain.transaction.entity;

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

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FixedTransaction extends BaseEntity {

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

    private LocalDateTime repeatDate;

    public void updateTitle(@NotBlank String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
    }

    public void updateMemo(String memo) {
        this.memo = memo;
    }

    public void updateAsset(@NotNull Long assetId) {
        if (assetId != null && assetId != 0) {
            this.assetId = assetId;
        }
    }

    public void updateType(@NotNull TransactionTypeEnum type) {
        if (type != null) {
            this.type = type;
        }
    }

    public void updateAmount(@NotNull BigDecimal amount) {
        if (amount != null) {
            this.amount = amount;
        }
    }

    public void updateCategory(@NotNull Long categoryId) {
        if (categoryId != null && categoryId != 0) {
            this.categoryId = categoryId;
        }
    }

    public void updateRepeatDate(@NotNull LocalDateTime date) {
        if (date != null) {
            this.repeatDate = date;
        }
    }
}
