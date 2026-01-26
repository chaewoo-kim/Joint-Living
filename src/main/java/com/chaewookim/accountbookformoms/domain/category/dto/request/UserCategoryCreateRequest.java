package com.chaewookim.accountbookformoms.domain.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCategoryCreateRequest(
        @NotBlank
        String title,

        @NotNull
        Boolean isInterested,

        @NotNull
        Boolean isIncome
) {
}
