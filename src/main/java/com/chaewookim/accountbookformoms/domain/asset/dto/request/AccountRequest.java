package com.chaewookim.accountbookformoms.domain.asset.dto.request;

import com.chaewookim.accountbookformoms.domain.asset.entity.Asset;
import com.chaewookim.accountbookformoms.domain.asset.entity.BankEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AccountRequest(

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

        Long id
) {
    public static Asset toEntity(AccountRequest accountRequest) {
        return Asset.builder()
                .username(accountRequest.username)
                .bank(accountRequest.bank)
                .accountNumber(accountRequest.accountNumber)
                .balance(accountRequest.balance)
                .build();
    }
}
