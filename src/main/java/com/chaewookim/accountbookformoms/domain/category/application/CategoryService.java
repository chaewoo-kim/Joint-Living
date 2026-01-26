package com.chaewookim.accountbookformoms.domain.category.application;

import com.chaewookim.accountbookformoms.domain.category.dao.UserCategoryRepository;
import com.chaewookim.accountbookformoms.domain.category.domain.UserCategory;
import com.chaewookim.accountbookformoms.domain.category.dto.request.UserCategoryCreateRequest;
import com.chaewookim.accountbookformoms.domain.category.dto.response.UserCreatedCategoryResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final UserCategoryRepository userCategoryRepository;

    public List<UserCreatedCategoryResponse> getAllUserCreatedCategories(Long userId) {
        // 사용자 카테고리 조회
        List<UserCategory> userCategories = userCategoryRepository.findAllByUserId(userId);

        return UserCreatedCategoryResponse.from(userCategories);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserCreatedCategoryResponse createUserCategory(Long userId, UserCategoryCreateRequest request) {
        // 사용자 카테고리 생성
        UserCategory savedUserCategory = userCategoryRepository.save(UserCategory.create(userId, request));

        return UserCreatedCategoryResponse.from(savedUserCategory);
    }

    public String deleteUserCategory(Long userId, Long categoryId) {
        return "success";
    }

    public UserCreatedCategoryResponse updateUserCategory(Long userId, Long categoryId) {

        return new UserCreatedCategoryResponse(1L, "title", 1L, true, true);
    }

    @Transactional
    public void createDefaultCategory(@NotNull Long userId) {
        userCategoryRepository.save(UserCategory.create(userId));
    }
}
