package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.transaction.dao.FixedTransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionFixRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.transaction.FixedTransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.entity.FixedTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FixedTransactionService {

    private final FixedTransactionRepository fixedTransactionRepository;

    @Transactional(rollbackFor = Exception.class)
    public FixedTransactionResponse createFix(TransactionFixRequest request, Long userId) {
        FixedTransaction fixedTransaction = FixedTransaction.builder()
                .title(request.title())
                .memo(request.memo())
                .userId(userId)
                .assetId(request.assetId())
                .categoryId(request.categoryId())
                .amount(request.amount())
                .type(request.type())
                .repeatDate(request.repeatDate())
                .build();

        fixedTransactionRepository.save(fixedTransaction);

        log.info("Created FixedTransaction with id {}", fixedTransaction.getId());

        return FixedTransactionResponse.from(fixedTransaction);
    }
}
