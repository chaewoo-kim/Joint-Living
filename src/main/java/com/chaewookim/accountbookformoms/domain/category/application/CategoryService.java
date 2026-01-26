package com.chaewookim.accountbookformoms.domain.category.application;

import com.chaewookim.accountbookformoms.domain.category.dao.UserCategoryRepository;
import com.chaewookim.accountbookformoms.domain.category.domain.UserCategory;
import com.chaewookim.accountbookformoms.domain.category.dto.request.UserCategoryCreateRequest;
import com.chaewookim.accountbookformoms.domain.category.dto.response.UserCreatedCategoryResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final UserCategoryRepository categoryRepository;

    public UserCreatedCategoryResponse createUserCategory(Long userId, UserCategoryCreateRequest request) {

        return new UserCreatedCategoryResponse(1L, "title", 1L);
    }

    public String deleteUserCategory(Long userId, Long categoryId) {
        return "success";
    }

    public UserCreatedCategoryResponse updateUserCategory(Long userId, Long categoryId) {

        return new UserCreatedCategoryResponse(1L, "title", 1L);
    }

    @Transactional
    public void createDefaultCategory(@NotNull Long userId) {
        categoryRepository.save(UserCategory.builder().userId(userId).build());
    }
}
