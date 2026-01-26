package com.chaewookim.accountbookformoms.domain.category.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreatedCategoryResponse(
        @NotNull
        Long id,

        @NotBlank
        String title,

        @NotNull
        Long userId
) {
}
