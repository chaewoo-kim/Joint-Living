package com.chaewookim.accountbookformoms.domain.asset.application;

import com.chaewookim.accountbookformoms.domain.asset.dao.AssetRepository;
import com.chaewookim.accountbookformoms.domain.asset.domain.Asset;
import com.chaewookim.accountbookformoms.domain.asset.domain.BankEnum;
import com.chaewookim.accountbookformoms.domain.asset.dto.request.AccountRequest;
import com.chaewookim.accountbookformoms.domain.asset.dto.response.AccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;

    @Mock
    private AssetRepository assetRepository;

    private String username;
    private Asset asset;
    private AccountRequest accountRequest;

    @BeforeEach
    void setUp() {
        Long userId = 1L;
        Long assetId = 10L;
        username = "username";

        asset = Asset.builder()
                .username(username)
                .balance(BigDecimal.valueOf(1000000))
                .bank(BankEnum.KB)
                .build();
        ReflectionTestUtils.setField(asset, "id", assetId);

        accountRequest = new AccountRequest(
                BankEnum.KB,
                "3333111133333",
                BigDecimal.valueOf(1000000),
                username
        );
    }

    @Test
    @DisplayName("요청 정보로 계좌가 생성되는지 확인")
    void createAccount() {
        // given
        given(assetRepository.save(any(Asset.class))).willReturn(asset);

        // when
        AccountResponse response = accountService.registAccount(username, accountRequest);

        // then
        assertNotNull(response);

        verify(assetRepository, times(1)).save(any(Asset.class));
    }
}