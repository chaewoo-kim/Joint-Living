package com.chaewookim.accountbookformoms.domain.asset.domain;


import com.chaewookim.accountbookformoms.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Asset extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BankEnum bank;

    @Column(nullable = false)
    private String accountNumber;

    @Builder.Default
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false)
    private String username;

    @Builder
    public Asset(BankEnum bank, String accountNumber, BigDecimal balance, String username) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.username = username;
    }

    public Asset updateBalance(BigDecimal newBalance) {
        this.balance = newBalance;

        return this;
    }

    public Asset updateUsername(String username) {
        this.username = username;

        return this;
    }
}
