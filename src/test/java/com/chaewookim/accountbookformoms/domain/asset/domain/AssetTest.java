package com.chaewookim.accountbookformoms.domain.asset.domain;

import com.chaewookim.accountbookformoms.domain.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssetTest {

    private Asset asset;

    @BeforeEach
    void setUp() {
        asset = Asset.builder()
                .bank(BankEnum.KB)
                .accountNumber("123")
                .userId(1L)
                .build();
        ReflectionTestUtils.setField(asset, "id", 1L);
    }

    @Test
    @DisplayName("Asset 객체 생성 시 기본값의 정상 적용")
    void createAsset_DefaultValues() {

        assertEquals(asset.getBalance(), BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Asset 객체 생성 후 updateBalance 정상 작동 확인")
    void createAsset_UpdateBalance() {
        asset.updateBalance(BigDecimal.TEN);

        assertEquals(asset.getBalance(), BigDecimal.TEN);
    }
}