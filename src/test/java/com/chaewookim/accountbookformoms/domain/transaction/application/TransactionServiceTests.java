package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionMemoRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dao.TransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionAccountRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionAmountRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionTitleUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionTypeRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.transaction.TransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.entity.Transaction;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTests {
    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    Long userId;
    Long assetId;
    Long newAssetId;
    Long transactionId;
    Long categoryId;

    BigDecimal amount;
    BigDecimal newAmount;

    private TransactionRequest transactionRequest;
    private TransactionTitleUpdate requestTitle;
    private TransactionMemoRequest requestMemo;
    private TransactionAccountRequest requestAccount;
    private TransactionAmountRequest requestAmount;
    private TransactionTypeRequest requestType;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        userId = 1L;
        assetId = 10L;
        newAssetId = 11L;
        transactionId = 20L;
        categoryId = 30L;

        amount = BigDecimal.valueOf(30000);
        newAmount = BigDecimal.valueOf(100000);

        transactionRequest = new TransactionRequest(
                "reqTitle",
                "reqMemo",
                userId,
                assetId,
                categoryId,
                BigDecimal.valueOf(30000),
                TransactionTypeEnum.EXPENSE,
                false,
                null
        );

        requestTitle =  new TransactionTitleUpdate(
                "newTitle"
        );

        requestMemo =  new TransactionMemoRequest(
                "newMemo"
        );

        requestAccount =  new TransactionAccountRequest(
                newAssetId
        );

        requestAmount =   new TransactionAmountRequest(
                newAmount
        );

        requestType =  new TransactionTypeRequest(
                TransactionTypeEnum.EXPENSE
        );

        transaction = Transaction.builder()
                .title("title")
                .memo("memo")
                .userId(userId)
                .assetId(assetId)
                .categoryId(categoryId)
                .amount(amount)
                .type(TransactionTypeEnum.INCOME)
                .isFixed(false)
                .build();
        ReflectionTestUtils.setField(transaction, "id", transactionId);
    }

    @Test
    @DisplayName("트랜잭션 생성 - 성공")
    void createTransaction() {
        // when
        TransactionResponse response = transactionService.createTransaction(transactionRequest, userId);

        // then
        assertNotNull(response);

        assertEquals(userId, response.userId());
        assertEquals(assetId, response.assetId());
        assertEquals("reqTitle", response.title());
    }


    @Test
    @DisplayName("트랜잭션 전체 조회 - 성공")
    void getAllTransaction_Success() {
        // given
        given(transactionRepository.findAllByUserId(userId)).willReturn(List.of(transaction));

        // when
        List<TransactionResponse> responses = transactionService.getAllTransactions(userId);

        // then
        assertNotNull(responses);

        assertEquals(1, responses.size());

        assertEquals("title", responses.get(0).title());
    }


    @Test
    @DisplayName("트랜잭션 타이틀 수정 - 성공")
    void updateTransactionTitle_Success() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.of(transaction));

        // when
        TransactionResponse response = transactionService.updateTransactionTitle(transactionId, requestTitle, userId);

        // then
        assertNotNull(response);

        assertEquals("newTitle", response.title());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }
    @Test
    @DisplayName("트랜잭션 타이틀 수정 - 실패 - TRANSACTION_NOT_FOUND")
    void updateTransactionTitle_Failure() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.empty());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () ->
                transactionService.updateTransactionTitle(transactionId, requestTitle, userId));

        assertEquals(ErrorCode.TRANSACTION_NOT_FOUND, exception.getErrorCode());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }


    @Test
    @DisplayName("트랜잭션 메모 수정 - 성공")
    void updateTransactionMemo_Success() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.of(transaction));

        // when
        TransactionResponse response = transactionService.updateTransactionMemo(transactionId, requestMemo, userId);

        // then
        assertNotNull(response);

        assertEquals("newMemo", response.memo());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }
    @Test
    @DisplayName("트랜잭션 메모 수정 - 실패 - TRANSACTION_NOT_FOUND")
    void updateTransactionMemo_Failure() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.empty());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () ->
                transactionService.updateTransactionMemo(transactionId, requestMemo, userId));

        assertEquals(ErrorCode.TRANSACTION_NOT_FOUND, exception.getErrorCode());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }


    @Test
    @DisplayName("트랜잭션 계좌 수정 - 성공")
    void updateTransactionAccount_Success() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.of(transaction));

        // when
        TransactionResponse response = transactionService.updateTransactionAccount(transactionId, requestAccount, userId);

        // then
        assertNotNull(response);

        assertEquals(newAssetId, response.assetId());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }
    @Test
    @DisplayName("트랜잭션 계좌 수정 - 실패 - TRANSACTION_NOT_FOUND")
    void updateTransactionAccount_Failure() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.empty());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () ->
                transactionService.updateTransactionAccount(transactionId, requestAccount, userId));

        assertEquals(ErrorCode.TRANSACTION_NOT_FOUND, exception.getErrorCode());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }


    @Test
    @DisplayName("트랜잭션 금액 수정 - 성공")
    void updateTransactionAmount_Success() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.of(transaction));

        // when
        TransactionResponse response = transactionService.updateTransactionAmount(transactionId, requestAmount, userId);

        // then
        assertNotNull(response);

        assertEquals(newAmount, response.amount());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }
    @Test
    @DisplayName("트랜잭션 금액 수정 - 실패 - TRANSACTION_NOT_FOUND")
    void updateTransactionAmount_Failure() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.empty());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () ->
                transactionService.updateTransactionAmount(transactionId, requestAmount, userId));

        assertEquals(ErrorCode.TRANSACTION_NOT_FOUND, exception.getErrorCode());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }


    @Test
    @DisplayName("트랜잭션 타입 수정 - 성공")
    void updateTransactionType_Success() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.of(transaction));

        // when
        TransactionResponse response = transactionService.updateTransactionType(transactionId, requestType, userId);

        // then
        assertNotNull(response);

        assertEquals(TransactionTypeEnum.EXPENSE, response.type());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }
    @Test
    @DisplayName("트랜잭션 타입 수정 - 실패 - TRANSACTION_NOT_FOUND")
    void updateTransactionType_Failure() {
        // given
        given(transactionRepository.findByIdAndUserId(transactionId, userId)).willReturn(Optional.empty());

        // when & then
        CustomException exception = assertThrows(CustomException.class, () ->
                transactionService.updateTransactionType(transactionId, requestType, userId));

        assertEquals(ErrorCode.TRANSACTION_NOT_FOUND, exception.getErrorCode());
        verify(transactionRepository, times(1)).findByIdAndUserId(transactionId, userId);
    }


    @Test
    @DisplayName("트랜잭션 삭제 - 성공")
    void deleteTransaction_Success() {
        // given
        given(transactionRepository.deleteByIdAndUserId(transactionId, userId)).willReturn(1L);

        // when
        transactionService.deleteTransaction(transactionId, userId);

        // then
        verify(transactionRepository, times(1)).deleteByIdAndUserId(transactionId, userId);
    }
    @Test
    @DisplayName("트랜잭션 삭제 - 실패")
    void deleteTransaction_Failure() {
        // given
        given(transactionRepository.deleteByIdAndUserId(transactionId, userId)).willReturn(0L);

        // then
        CustomException exception = assertThrows(CustomException.class, () ->
                transactionService.deleteTransaction(transactionId, userId));
        assertEquals(ErrorCode.TRANSACTION_NOT_FOUND, exception.getErrorCode());

        verify(transactionRepository, times(1)).deleteByIdAndUserId(transactionId, userId);
    }
}