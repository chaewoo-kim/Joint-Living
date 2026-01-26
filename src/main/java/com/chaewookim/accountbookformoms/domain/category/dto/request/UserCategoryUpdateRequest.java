package com.chaewookim.accountbookformoms.domain.category.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserCategoryUpdateRequest(
        @NotBlank
        String title
) {
}
