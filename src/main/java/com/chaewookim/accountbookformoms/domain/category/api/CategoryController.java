package com.chaewookim.accountbookformoms.domain.category.api;

import com.chaewookim.accountbookformoms.domain.category.application.CategoryService;
import com.chaewookim.accountbookformoms.domain.category.dto.request.UserCategoryCreateRequest;
import com.chaewookim.accountbookformoms.domain.category.dto.request.UserCategoryUpdateRequest;
import com.chaewookim.accountbookformoms.domain.category.dto.response.UserCreatedCategoryResponse;
import com.chaewookim.accountbookformoms.domain.user.domain.CustomUserDetails;
import com.chaewookim.accountbookformoms.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "계좌", description = "계좌 등록/계좌 조회/계좌 삭제/계좌 잔고 입력 및 수정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "사용자 카테고리 생성", description = "기존 카테고리가 아닌 사용자가 이름을 지정한 카테고리 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<UserCreatedCategoryResponse>> createUserCategory(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UserCategoryCreateRequest request
    ) {
        Long userId = userDetails.getUserId();

        return ResponseEntity.ok(ApiResponse.success(categoryService.createUserCategory(userId, request)));
    }

    @Operation(summary = "사용자 카테고리 삭제", description = "기존 카테고리가 아닌 사용자가 생성한 카테고리 삭제")
    @DeleteMapping("/{category-id}")
    public ResponseEntity<ApiResponse<String>> deleteUserCategory(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("category-id") Long categoryId
    ) {
        Long userId = userDetails.getUserId();

        return ResponseEntity.ok(ApiResponse.success(categoryService.deleteUserCategory(userId, categoryId)));
    }

    @Operation(summary = "사용자 카테고리 수정", description = "기존 카테고리가 아닌 사용자가 생성한 카테고리 수정")
    @PatchMapping("/{category-id}")
    public ResponseEntity<ApiResponse<UserCreatedCategoryResponse>> updateUserCategory(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UserCategoryUpdateRequest request,
            @PathVariable("category-id") Long categoryId
    ) {
        Long userId = userDetails.getUserId();

        return ResponseEntity.ok(ApiResponse.success(categoryService.updateUserCategory(userId, categoryId)));
    }
}
