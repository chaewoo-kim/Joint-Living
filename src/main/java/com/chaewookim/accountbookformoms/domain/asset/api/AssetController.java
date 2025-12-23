package com.chaewookim.accountbookformoms.domain.asset.api;

import com.chaewookim.accountbookformoms.domain.asset.application.AssetService;
import com.chaewookim.accountbookformoms.domain.asset.dto.request.AccountRequest;
import com.chaewookim.accountbookformoms.domain.asset.dto.response.AccountResponse;
import com.chaewookim.accountbookformoms.domain.user.domain.CustomUserDetails;
import com.chaewookim.accountbookformoms.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "계좌", description = "계좌 등록/계좌 조회/계좌 삭제/계좌 잔고 입력 및 수정 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AssetController {

    private final AssetService assetService;

    @Operation(summary = "계좌 등록", description = "사용자가 수입, 지출, 자산 관리에 사용할 계좌를 등록한다.")
    @PostMapping("/regist")
    public ResponseEntity<ApiResponse<AccountResponse>> registAccount(
            @RequestBody AccountRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        String username = userDetails.getUsername();

        return ResponseEntity.ok(ApiResponse.success(assetService.registAccount(username, request)));
    }

    @Operation(summary = "계좌 조회", description = "사용자가 등록한 본인의 계좌를 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountResponse>>> getAccounts(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        String username = userDetails.getUsername();

        return ResponseEntity.ok(ApiResponse.success(assetService.getAllAccounts(username)));
    }

    @Operation(summary = "계좌 삭제", description = "등록된 사용자의 계좌를 삭제한다.")
    @DeleteMapping("/delete/{account-number}")
    public ResponseEntity<ApiResponse<String>> deleteAccount(
            @PathVariable("account-number") String accountNumber,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        String username = userDetails.getUsername();

        assetService.deleteAccount(username, accountNumber);

        return ResponseEntity.ok(ApiResponse.success("계좌 삭제 완료"));
    }

    @Operation(summary = "계좌 잔고 수정", description = "사용자의 계좌 잔고를 수정한다.")
    @GetMapping("/update")
    public ResponseEntity<ApiResponse<AccountResponse>> updateAccount(
            @RequestBody AccountRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        String username = userDetails.getUsername();

        return ResponseEntity.ok(ApiResponse.success(assetService.updateAccount(request)));
    }
}
