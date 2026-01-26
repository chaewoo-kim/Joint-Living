package com.chaewookim.accountbookformoms.domain.category.domain;

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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StandardCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String categoryName;

    @Column(nullable = false)
    private boolean isInterested = false;

    @Column(nullable = false)
    private boolean isIncome = false;    // income or expense

    @Builder
    public StandardCategory(String categoryName, boolean isInterested, boolean isIncome) {
        this.categoryName = categoryName;
        this.isInterested = isInterested;
        this.isIncome = isIncome;
    }

    public StandardCategory updateCategoryName(String categoryName) {
        this.categoryName = categoryName;

        return this;
    }

    public StandardCategory updateIsInterested(boolean isInterested) {
        this.isInterested = isInterested;

        return this;
    }
}
