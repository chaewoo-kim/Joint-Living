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
import com.chaewookim.accountbookformoms.global.error.CustomException;
import com.chaewookim.accountbookformoms.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse createTransaction(TransactionRequest request, Long userId) {
        Transaction transaction = Transaction.from(request, userId);

        transactionRepository.save(transaction);

        return TransactionResponse.from(transaction);
    }

    public List<TransactionResponse> getAllTransactions(Long userId) {
        return Transaction.from(transactionRepository.findAllByUserId(userId));
    }

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse updateTransactionTitle(Long id, TransactionTitleUpdate requestTitle, Long userId) {
        Transaction target = transactionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        target.updateTitle(requestTitle.title());

        return TransactionResponse.from(target);
    }

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse updateTransactionMemo(Long id, TransactionMemoRequest requestMemo, Long userId) {
        Transaction target = transactionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        target.updateMemo(requestMemo.memo());

        return TransactionResponse.from(target);
    }

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse updateTransactionAccount(Long id, TransactionAccountRequest requestAccount, Long userId) {
        Transaction target = transactionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        target.updateAsset(requestAccount.assetId());

        return TransactionResponse.from(target);
    }

    public TransactionResponse updateTransactionAmount(Long id, TransactionAmountRequest requestAmount, Long userId) {
        Transaction target = transactionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        target.updateAmount(requestAmount.amount());

        return TransactionResponse.from(target);
    }

    public TransactionResponse updateTransactionType(Long id, TransactionTypeRequest requestType, Long userId) {
        Transaction target = transactionRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));

        target.updateType(requestType.type());

        return TransactionResponse.from(target);
    }
}
