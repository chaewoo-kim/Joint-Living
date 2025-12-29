package com.chaewookim.accountbookformoms.domain.transaction.api;

import com.chaewookim.accountbookformoms.domain.transaction.application.FixedTransactionService;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionFixRequest;
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
            @RequestBody TransactionFixRequest request,
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

    @Operation(description = "고정 수입/고정 지출 수정")
    @PatchMapping
    public ResponseEntity<ApiResponse<FixedTransactionResponse>> updateFix (
            @RequestBody TransactionFixRequest request,
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        Long userId = user.getUserId();

        return ResponseEntity.ok(ApiResponse.success(null));
//        return ResponseEntity.ok(ApiResponse.success(fixedTransactionService.updateFix(request, userId)));
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
