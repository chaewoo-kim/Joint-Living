package com.chaewookim.accountbookformoms.domain.asset.application;

import com.chaewookim.accountbookformoms.domain.asset.dao.AssetRepository;
import com.chaewookim.accountbookformoms.domain.asset.domain.Asset;
import com.chaewookim.accountbookformoms.domain.asset.dto.request.AccountRequest;
import com.chaewookim.accountbookformoms.domain.asset.dto.response.AccountResponse;
import com.chaewookim.accountbookformoms.global.error.CustomException;
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

        return AccountResponse.from(assetRepository.save(asset));
    }

    public void deleteAccount(String id) {


    }

    public AccountResponse updateAccount(AccountRequest request) {

        return null;
    }
}
