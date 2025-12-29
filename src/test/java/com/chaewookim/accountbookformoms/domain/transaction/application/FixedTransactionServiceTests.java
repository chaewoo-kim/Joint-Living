package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.transaction.dao.FixedTransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionTitleUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.transaction.FixedTransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.entity.FixedTransaction;
import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import com.chaewookim.accountbookformoms.global.error.CustomException;
import com.chaewookim.accountbookformoms.global.error.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class FixedTransactionServiceTests {
    @InjectMocks
    private FixedTransactionService fixedTransactionService;

    @Mock
    private FixedTransactionRepository fixedTransactionRepository;

    Long userId;
    Long assetId;
    Long fixedTransactionId;
    Long categoryId;
    BigDecimal amount;

    private FixedTransaction fixedTransaction;
    private FixedTransactionRequest fixedTransactionRequest;
    private FixedTransactionTitleUpdate  fixedTransactionTitleUpdate;

    @BeforeEach
    void setUp() {
        userId = 1L;
        assetId = 10L;
        fixedTransactionId = 20L;
        categoryId = 30L;

        amount = BigDecimal.valueOf(300000);

        fixedTransaction = FixedTransaction.builder()
                .title("title")
                .memo("memo")
                .userId(userId)
                .assetId(assetId)
                .categoryId(categoryId)
                .amount(amount)
                .type(TransactionTypeEnum.EXPENSE)
                .repeatDate(LocalDateTime.now())
                .build();
        ReflectionTestUtils.setField(fixedTransaction, "id", fixedTransactionId);

        fixedTransactionRequest = new FixedTransactionRequest(
                "reqTitle",
                "reqMemo",
                userId,
                assetId,
                categoryId,
                amount,
                TransactionTypeEnum.INCOME,
                LocalDateTime.now()
        );

        fixedTransactionTitleUpdate = new FixedTransactionTitleUpdate(
                "updateTitle"
        );
    }

    @Test
    @DisplayName("고정지출 추가 - 성공")
    void createFixedExpense_Success() {
        // when
        FixedTransactionResponse response = fixedTransactionService.createFix(fixedTransactionRequest, userId);

        // then
        assertNotNull(response);
        assertEquals("title", fixedTransaction.getTitle());
    }


    @Test
    @DisplayName("고정지출/고정수입 조회 - 성공")
    void getAllFixedTransactions_Success() {
        // given
        given(fixedTransactionRepository.findAllByUserId(userId)).willReturn(List.of(fixedTransaction));

        // when
        List<FixedTransactionResponse> response = fixedTransactionService.getAllFixedTransactions(userId);

        // then
        assertNotNull(response);

        assertEquals(1, response.size());
        assertEquals("title", response.get(0).title());
    }
    @Test
    @DisplayName("고정지출/고정수입 조회 - 실패 - 조회 결과 없음")
    void getAllFixedTransactions_Failure() {
        // given
        given(fixedTransactionRepository.findAllByUserId(userId)).willReturn(Collections.emptyList());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () -> fixedTransactionService.getAllFixedTransactions(userId));
        assertEquals(ErrorCode.NO_FIXED_TRANSACTIONS, exception.getErrorCode());
    }


    @Test
    @DisplayName("고정 트랜잭션 타이틀 수정 - 성공")
    void updateTitle_Success() {
        // given
        given(fixedTransactionRepository.findByIdAndUserId(fixedTransactionId, userId)).willReturn(Optional.of(fixedTransaction));

        // when
        FixedTransactionResponse response = fixedTransactionService.updateFix(fixedTransactionTitleUpdate, userId, fixedTransactionId);

        // then
        assertNotNull(response);
        assertEquals("updateTitle", fixedTransactionTitleUpdate.title());
    }
    @Test
    @DisplayName("고정 트랜잭션 타이틀 수정 - 실패 - 조회 결과 없음")
    void updateTitle_Failure() {
        // given
        given(fixedTransactionRepository.findByIdAndUserId(fixedTransactionId, userId)).willReturn(Optional.empty());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () -> fixedTransactionService.updateFix(fixedTransactionTitleUpdate, userId, fixedTransactionId));
        assertEquals(ErrorCode.NO_FIXED_TRANSACTIONS, exception.getErrorCode());
    }
}