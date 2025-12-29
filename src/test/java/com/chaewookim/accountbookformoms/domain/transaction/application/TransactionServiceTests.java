package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.transaction.api.TransactionMemoRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dao.TransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionAccountRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionAmountRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionTitleUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionTypeRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.TransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.entity.Transaction;
import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
                TransactionTypeEnum.INCOME
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
}