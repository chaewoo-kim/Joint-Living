package com.chaewookim.accountbookformoms.domain.asset.dto.response;

import com.chaewookim.accountbookformoms.domain.asset.entity.Asset;
import com.chaewookim.accountbookformoms.domain.asset.entity.BankEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public record AccountResponse(

        @NotNull(message = "은행은 필수입니다.")
        BankEnum bank,

        @NotBlank(message = "계좌번호는 필수입니다.")
        @Size(min = 7, max = 20)
        String accountNumber,

        @NotNull
        @Size(min = 0)
        BigDecimal balance,

        @NotNull
        String username,

        @NotNull
        Long id
) {
    public static AccountResponse from(Asset asset) {
        return  new AccountResponse(
                asset.getBank(),
                asset.getAccountNumber(),
                asset.getBalance(),
                asset.getUsername(),
                asset.getId()
        );
    }

    public static List<AccountResponse> from(List<Asset> assets) {
        return assets.stream()
                .map(AccountResponse::from)
                .toList();
    }
}

