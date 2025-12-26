package com.chaewookim.accountbookformoms.domain.transaction.api;

import com.chaewookim.accountbookformoms.domain.transaction.application.TransactionService;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionFixRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.TransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionRequest;
import com.chaewookim.accountbookformoms.domain.user.domain.CustomUserDetails;
import com.chaewookim.accountbookformoms.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "수입/지출", description = "수입/지출 입력, 수정, 삭제에 관한 API")
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

    @Operation(description = "수입/지출 수정")
    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransaction(
            @RequestBody TransactionRequest request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(transactionService.updateTransaction(request, userId)));
    }

    @Operation(description = "수입/지출 삭제")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<TransactionResponse>> deleteTransaction(
            @PathVariable String id,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();
        Long transactionId = Long.parseLong(id);

        return ResponseEntity.ok(ApiResponse.success(transactionService.deleteTransaction(transactionId, userId)));
    }
}
