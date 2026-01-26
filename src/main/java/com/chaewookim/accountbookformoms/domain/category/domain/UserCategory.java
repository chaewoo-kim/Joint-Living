package com.chaewookim.accountbookformoms.domain.category.domain;

import com.chaewookim.accountbookformoms.domain.category.dto.request.UserCategoryCreateRequest;
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
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Builder.Default
    @Column(nullable = false, unique = true)
    private String title = "기본 이름";

    @Builder.Default
    @Column(nullable = false)
    private Boolean isInterested = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isIncome = false;   // income or expense

    public static UserCategory create(Long userId) {
        return UserCategory.builder()
                .userId(userId)
                .build();
    }

    public static UserCategory create(Long userId, UserCategoryCreateRequest request) {
        return UserCategory.builder()
                .userId(userId)
                .title(request.title())
                .isInterested(request.isInterested())
                .isIncome(request.isIncome())
                .build();
    }
}
