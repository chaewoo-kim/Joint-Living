package com.chaewookim.accountbookformoms.domain.category.dto.response;

import com.chaewookim.accountbookformoms.domain.category.domain.UserCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserCreatedCategoryResponse(
        @NotNull
        Long id,

        @NotBlank
        String title,

        @NotNull
        Long userId,

        @NotNull
        Boolean isInterested,

        @NotNull
        Boolean isIncome
) {
    public static UserCreatedCategoryResponse from(UserCategory savedUserCategory) {
        return new UserCreatedCategoryResponse(
                savedUserCategory.getId(),
                savedUserCategory.getTitle(),
                savedUserCategory.getUserId(),
                savedUserCategory.getIsInterested(),
                savedUserCategory.getIsIncome()
        );
    }

    public static List<UserCreatedCategoryResponse> from(List<UserCategory> userCategories) {
        return userCategories.stream()
                .map(UserCreatedCategoryResponse::from)
                .toList();
    }
}
