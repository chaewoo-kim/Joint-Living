package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.transaction.dao.FixedTransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionFixRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.transaction.FixedTransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.entity.FixedTransaction;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    private TransactionFixRequest transactionFixRequest;

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

        transactionFixRequest = new TransactionFixRequest(
                "reqTitle",
                "reqMemo",
                userId,
                assetId,
                categoryId,
                amount,
                TransactionTypeEnum.INCOME,
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("고정지출 추가 - 성공")
    void createFixedExpense_Success() {
        // when
        FixedTransactionResponse response = fixedTransactionService.createFix(transactionFixRequest, userId);

        // then
        assertNotNull(response);
        assertEquals("title", fixedTransaction.getTitle());
    }
}