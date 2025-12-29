package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.transaction.dao.FixedTransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionTitleUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.transaction.FixedTransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.entity.FixedTransaction;
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
public class FixedTransactionService {

    private final FixedTransactionRepository fixedTransactionRepository;

    @Transactional(rollbackFor = Exception.class)
    public FixedTransactionResponse createFix(FixedTransactionRequest request, Long userId) {
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

    public List<FixedTransactionResponse> getAllFixedTransactions(Long userId) {
        List<FixedTransaction> fixedTransactionList = fixedTransactionRepository.findAllByUserId(userId);

        int result = fixedTransactionList.size();

        if (result == 0) {
            throw new CustomException(ErrorCode.NO_FIXED_TRANSACTIONS);
        }

        return FixedTransactionResponse.from(fixedTransactionList);
    }

    @Transactional(rollbackFor = Exception.class)
    public FixedTransactionResponse updateFixTitle(FixedTransactionTitleUpdate request, Long userId, Long id) {
        FixedTransaction fixedTransaction = fixedTransactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_FIXED_TRANSACTIONS));

        fixedTransaction.updateTitle(request.title());

        return FixedTransactionResponse.from(fixedTransaction);
    }
}
