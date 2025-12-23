package com.chaewookim.accountbookformoms.domain.asset.application;

import com.chaewookim.accountbookformoms.domain.asset.dao.AssetRepository;
import com.chaewookim.accountbookformoms.domain.asset.domain.Asset;
import com.chaewookim.accountbookformoms.domain.asset.dto.request.AccountRequest;
import com.chaewookim.accountbookformoms.domain.asset.dto.response.AccountResponse;
import com.chaewookim.accountbookformoms.global.error.CustomException;
import com.chaewookim.accountbookformoms.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AssetRepository assetRepository;

    @Transactional(rollbackFor = CustomException.class)
    public AccountResponse registAccount(String username, AccountRequest request) {
        Asset asset = AccountRequest.toEntity(request);

        return AccountResponse.from(assetRepository.save(asset.updateUsername(username)));
    }

    @Transactional(rollbackFor = CustomException.class)
    public void deleteAccount(String username, String accountNumber) {
        Asset asset = assetRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomException(ErrorCode.ACCOUNT_NOT_FOUND));

        if (!username.equals(asset.getUsername())) {
            throw new CustomException(ErrorCode.ACCOUNT_ACCESS_DENIED);
        }

        assetRepository.deleteByAccountNumber(accountNumber);
    }

    public AccountResponse updateAccount(AccountRequest request) {

        return null;
    }
}
