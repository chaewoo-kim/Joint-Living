package com.chaewookim.accountbookformoms.domain.transaction.api;

import com.chaewookim.accountbookformoms.domain.transaction.application.FixedTransactionService;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionAccountUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionAmountUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionCategoryUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionMemoUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionRepeatDateUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionTitleUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionTypeUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.transaction.FixedTransactionResponse;
import com.chaewookim.accountbookformoms.domain.user.domain.CustomUserDetails;
import com.chaewookim.accountbookformoms.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/fixed-transactions")
public class FixedTransactionController {

    private final FixedTransactionService fixedTransactionService;

    @Operation(summary = "고정 수입/고정 지출 추가", description = "고정 수입/고정 지출을 사용자 정보로 추가")
    @PostMapping
    public ResponseEntity<ApiResponse<FixedTransactionResponse>> createFix (
            @RequestBody FixedTransactionRequest request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.createFix(request, userId)));
    }

    @Operation(summary = "고정 수입/고정 지출 조회", description = "사용자가 등록한 고정 수입과 고정 지출 전부 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<FixedTransactionResponse>>> getFix (
        @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.getAllFixedTransactions(userId)));
    }

    @Operation(summary = "고정 수입/고정 지출 타이틀 수정", description = "고정 수입/고정 지출에 대한 타이틀(제목) 수정")
    @PatchMapping("{id}/title")
    public ResponseEntity<ApiResponse<FixedTransactionResponse>> updateFixedTransaction (
            @PathVariable Long id,
            @RequestBody FixedTransactionTitleUpdate request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.updateFix(request, userId, id)));
    }

    @Operation(summary = "고정 수입/고정 지출 메모 수정", description = "고정 수입/고정 지출에 대한 메모 수정")
    @PatchMapping("{id}/memo")
    public ResponseEntity<ApiResponse<FixedTransactionResponse>> updateFixedTransaction (
            @PathVariable Long id,
            @RequestBody FixedTransactionMemoUpdate request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.updateFix(request, userId, id)));
    }

    @Operation(summary = "고정 수입/고정 지출 계좌 수정", description = "고정 수입/고정 지출에 대한 지불/지급 계좌 수정")
    @PatchMapping("{id}/account")
    public ResponseEntity<ApiResponse<FixedTransactionResponse>> updateFixedTransaction (
            @PathVariable Long id,
            @RequestBody FixedTransactionAccountUpdate request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.updateFix(request, userId, id)));
    }

    @Operation(summary = "고정 수입/고정 지출 카테고리 수정", description = "고정 수입/고정 지출에 대한 카테고리 수정")
    @PatchMapping("{id}/category")
    public ResponseEntity<ApiResponse<FixedTransactionResponse>> updateFixedTransaction (
            @PathVariable Long id,
            @RequestBody FixedTransactionCategoryUpdate request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.updateFix(request, userId, id)));
    }

    @Operation(summary = "고정 수입/고정 지출 금액 수정", description = "고정 수입/고정 지출에 대한 지불/지급받는 금액 수정")
    @PatchMapping("{id}/amount")
    public ResponseEntity<ApiResponse<FixedTransactionResponse>> updateFixedTransaction (
            @PathVariable Long id,
            @RequestBody FixedTransactionAmountUpdate request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.updateFix(request, userId, id)));
    }

    @Operation(summary = "고정 수입/고정 지출 타입 수정", description = "고정 수입/고정 지출에 대해 수입인지 지출인지 수정")
    @PatchMapping("{id}/type")
    public ResponseEntity<ApiResponse<FixedTransactionResponse>> updateFixedTransaction (
            @PathVariable Long id,
            @RequestBody FixedTransactionTypeUpdate request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.updateFix(request, userId, id)));
    }

    @Operation(summary = "고정 수입/고정 지출 날짜 수정", description = "고정 수입/고정 지출에 대해 지불/지급 날짜 수정")
    @PatchMapping("{id}/repeat-date")
    public ResponseEntity<ApiResponse<FixedTransactionResponse>> updateFixedTransaction (
            @PathVariable Long id,
            @RequestBody FixedTransactionRepeatDateUpdate request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.updateFix(request, userId, id)));
    }

    @Operation(description = "고정 수입/고정 지출 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteFix (
            @PathVariable String id,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();
        Long transactionId = Long.parseLong(id);

//        fixedTransactionService.deleteFix(transactionId, userId);

        return ResponseEntity.ok(ApiResponse.success(null));
//        return ResponseEntity.ok(ApiResponse.success("삭제 완료"));
    }
}
