package com.chaewookim.accountbookformoms.domain.asset.domain;

import com.chaewookim.accountbookformoms.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Budget extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long userId;

    @Column(unique = true, nullable = false)
    private LocalDate targetMonth;

    @Builder.Default
    @Column(nullable = false)
    private BigDecimal budgetAmount = BigDecimal.ZERO;

    @Builder.Default
    @Column(nullable = false)
    private BigDecimal estimatedExpense = BigDecimal.ZERO;

    @Column(nullable = false)
    private boolean isPublic = false;

    @Builder.Default
    @Column(nullable = false)
    private BigDecimal totalIncome = BigDecimal.ZERO;

    @Builder.Default
    @Column(nullable = false)
    private BigDecimal totalExpense = BigDecimal.ZERO;

    @Builder
    public Budget(Long userId, LocalDate targetMonth, BigDecimal budgetAmount, BigDecimal estimatedExpense, boolean isPublic, BigDecimal totalIncome, BigDecimal totalExpense) {
        this.userId = userId;
        this.targetMonth = targetMonth;
        this.budgetAmount = budgetAmount;
        this.estimatedExpense = estimatedExpense;
        this.isPublic = isPublic;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
    }

    public Budget updateAll(BigDecimal budgetAmount, BigDecimal estimatedExpense, BigDecimal totalIncome, BigDecimal totalExpense) {
        this.budgetAmount = budgetAmount;
        this.estimatedExpense = estimatedExpense;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;

        return this;
    }

    public Budget updateBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;

        return this;
    }

    public Budget updateEstimatedExpense(BigDecimal estimatedExpense) {
        this.estimatedExpense = estimatedExpense;

        return this;
    }

    public Budget updateTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;

        return this;
    }

    public Budget updateTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;

        return this;
    }

    public Budget updateIsPublic(boolean isPublic) {
        this.isPublic = isPublic;

        return this;
    }
}
