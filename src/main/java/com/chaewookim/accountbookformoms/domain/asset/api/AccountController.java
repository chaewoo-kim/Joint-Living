package com.chaewookim.accountbookformoms.domain.asset.api;

import com.chaewookim.accountbookformoms.domain.asset.application.AccountService;
import com.chaewookim.accountbookformoms.domain.asset.dto.request.AccountRequest;
import com.chaewookim.accountbookformoms.domain.asset.dto.response.AccountResponse;
import com.chaewookim.accountbookformoms.domain.user.domain.CustomUserDetails;
import com.chaewookim.accountbookformoms.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "계좌", description = "계좌 등록/계좌 삭제/계좌 잔고 입력 및 수정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Operation(summary = "계좌 등록", description = "사용자가 수입, 지출, 자산 관리에 사용할 계좌를 등록한다.")
    @GetMapping("/regist")
    public ResponseEntity<ApiResponse<AccountResponse>> registAccount(
            @RequestBody AccountRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        String username = userDetails.getUsername();

        return ResponseEntity.ok(ApiResponse.success(accountService.registAccount(username, request)));
    }

    @Operation(summary = "계좌 삭제", description = "등록된 사용자의 계좌를 삭제한다.")
    @GetMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteAccount(
            @PathVariable String id,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        accountService.deleteAccount(id);

        return ResponseEntity.ok(ApiResponse.success("계좌 삭제 완료"));
    }

    @Operation(summary = "계좌 잔고 수정", description = "사용자의 계좌 잔고를 수정한다.")
    @GetMapping("/update")
    public ResponseEntity<ApiResponse<AccountResponse>> updateAccount(@RequestBody AccountRequest request) {

        return ResponseEntity.ok(ApiResponse.success(accountService.updateAccount(request)));
    }
}
