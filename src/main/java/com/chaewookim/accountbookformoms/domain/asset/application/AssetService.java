package com.chaewookim.accountbookformoms.domain.asset.application;

import com.chaewookim.accountbookformoms.domain.asset.dao.AssetRepository;
import com.chaewookim.accountbookformoms.domain.asset.entity.Asset;
import com.chaewookim.accountbookformoms.domain.asset.dto.request.AccountRequest;
import com.chaewookim.accountbookformoms.domain.asset.dto.request.AccountUpdateRequest;
import com.chaewookim.accountbookformoms.domain.asset.dto.response.AccountResponse;
import com.chaewookim.accountbookformoms.global.error.CustomException;
import com.chaewookim.accountbookformoms.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;

    @Transactional(rollbackFor = CustomException.class)
    public AccountResponse registAccount(String username, AccountRequest request) {
        Asset asset = AccountRequest.toEntity(request);

        return AccountResponse.from(assetRepository.save(asset.updateUsername(username)));
    }

    public List<AccountResponse> getAllAccounts(String username) {
        return AccountResponse.from(assetRepository.findAllByUsername(username));
    }

    @Transactional(rollbackFor = CustomException.class)
    public void deleteAccount(String username, String accountNumber) {
        Asset asset = assetRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomException(ErrorCode.ACCOUNT_NOT_FOUND));

        log.info("username = {}",  username);
        log.info("account userName = {}", asset.getUsername());
        if (!username.equals(asset.getUsername())) {
            throw new CustomException(ErrorCode.ACCOUNT_ACCESS_DENIED);
        }

        assetRepository.deleteByAccountNumber(accountNumber);
    }

    @Transactional(rollbackFor = CustomException.class)
    public AccountResponse updateAccount(String username, AccountUpdateRequest request) {
        return AccountResponse.from(
                assetRepository.findByIdAndUsername(request.id(), username)
                        .orElseThrow(() -> new CustomException(ErrorCode.ACCOUNT_ACCESS_DENIED))
                        .updateBalance(request.balance())
        );
    }
}
