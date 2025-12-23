package com.chaewookim.accountbookformoms.domain.asset.dto.response;

import com.chaewookim.accountbookformoms.domain.asset.domain.Asset;
import com.chaewookim.accountbookformoms.domain.asset.domain.BankEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

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
        String username
) {
    public static AccountResponse from(Asset asset) {
        return  new AccountResponse(
                asset.getBank(),
                asset.getAccountNumber(),
                asset.getBalance(),
                asset.getUsername()
        );
    }
}

