package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.transaction.dao.FixedTransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionAccountUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionAmountUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionCategoryUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionMemoUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionRepeatDateUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionTitleUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.fixedtransaction.FixedTransactionTypeUpdate;
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
    public FixedTransactionResponse updateFix(FixedTransactionTitleUpdate request, Long userId, Long id) {
        return FixedTransactionResponse.from(getFixedTransactionByIdAndUserId(id, userId).updateTitle(request.title()));
    }

    @Transactional(rollbackFor = Exception.class)
    public FixedTransactionResponse updateFix(FixedTransactionMemoUpdate request, Long userId, Long id) {
        return FixedTransactionResponse.from(getFixedTransactionByIdAndUserId(id, userId).updateMemo(request.memo()));
    }

    @Transactional(rollbackFor = Exception.class)
    public FixedTransactionResponse updateFix(FixedTransactionAccountUpdate request, Long userId, Long id) {
        return FixedTransactionResponse.from(getFixedTransactionByIdAndUserId(id, userId).updateAsset(request.accountId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public FixedTransactionResponse updateFix(FixedTransactionCategoryUpdate request, Long userId, Long id) {
        return FixedTransactionResponse.from(getFixedTransactionByIdAndUserId(id, userId).updateCategory(request.categoryId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public FixedTransactionResponse updateFix(FixedTransactionAmountUpdate request, Long userId, Long id) {
        return FixedTransactionResponse.from(getFixedTransactionByIdAndUserId(id, userId).updateAmount(request.amount()));
    }

    @Transactional(rollbackFor = Exception.class)
    public FixedTransactionResponse updateFix(FixedTransactionRepeatDateUpdate request, Long userId, Long id) {
        return FixedTransactionResponse.from(getFixedTransactionByIdAndUserId(id, userId).updateRepeatDate(request.date()));
    }

    @Transactional(rollbackFor = Exception.class)
    public FixedTransactionResponse updateFix(FixedTransactionTypeUpdate request, Long userId, Long id) {
        return FixedTransactionResponse.from(getFixedTransactionByIdAndUserId(id, userId).updateType(request.type()));
    }

    private FixedTransaction getFixedTransactionByIdAndUserId(Long id, Long userId) {
        return fixedTransactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_FIXED_TRANSACTIONS));
    }
}
