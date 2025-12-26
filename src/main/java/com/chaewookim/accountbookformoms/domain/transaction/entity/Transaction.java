package com.chaewookim.accountbookformoms.domain.transaction.entity;

import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import com.chaewookim.accountbookformoms.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
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

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private TransactionTypeEnum type;

    @Column(nullable = false)
    private Boolean isFixed;

    private LocalDateTime repeatDate; // 고정 지출, 고정 수입일 경우
}
