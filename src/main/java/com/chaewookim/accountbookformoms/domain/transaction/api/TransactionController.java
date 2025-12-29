package com.chaewookim.accountbookformoms.domain.transaction.api;

import com.chaewookim.accountbookformoms.domain.transaction.application.TransactionService;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionAccountRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionAmountRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionTitleUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionTypeRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.TransactionResponse;
import com.chaewookim.accountbookformoms.domain.user.domain.CustomUserDetails;
import com.chaewookim.accountbookformoms.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "수입/지출", description = "수입/지출 입력, 수정, 삭제, 조회에 관한 API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(description = "수입/지출 입력")
    @PostMapping
    public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(
            @RequestBody TransactionRequest request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(transactionService.createTransaction(request, userId)));
    }

    @Operation(description = "수입/지출 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactions(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(transactionService.getAllTransactions(userId)));
    }

    @Operation(description = "수입/지출 제목 수정")
    @PatchMapping("/{id}/title")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransactionTitle(
            @PathVariable Long id,
            @RequestBody TransactionTitleUpdate requestTitle,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(transactionService.updateTransactionTitle(id, requestTitle, userId)));
    }

    @Operation(description = "수입/지출 메모 수정")
    @PatchMapping("/{id}/memo")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransactionMemo(
            @PathVariable Long id,
            @RequestBody TransactionMemoRequest requestMemo,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(transactionService.updateTransactionMemo(id, requestMemo, userId)));
    }

    @Operation(description = "수입/지출 계좌 수정")
    @PatchMapping("/{id}/account")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransactionAccount(
            @PathVariable Long id,
            @RequestBody TransactionAccountRequest requestAccount,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(transactionService.updateTransactionAccount(id, requestAccount, userId)));
    }

    @Operation(description = "수입/지출 금액 수정")
    @PatchMapping("/{id}/amount")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransactionAmount(
            @PathVariable Long id,
            @RequestBody TransactionAmountRequest requestAmount,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(transactionService.updateTransactionAmount(id, requestAmount, userId)));
    }

    @Operation(description = "수입/지출 타입 수정")
    @PatchMapping("/{id}/type")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransactionType(
            @PathVariable Long id,
            @RequestBody TransactionTypeRequest requestType,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(transactionService.updateTransactionType(id, requestType, userId)));
    }

    @Operation(description = "수입/지출 삭제")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse<String>> deleteTransaction(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        transactionService.deleteTransaction(id, userId);

        return ResponseEntity.ok(ApiResponse.success("삭제 완료"));
    }
}
