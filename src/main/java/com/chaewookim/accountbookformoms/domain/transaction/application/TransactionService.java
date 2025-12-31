package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.asset.dao.AssetRepository;
import com.chaewookim.accountbookformoms.domain.asset.entity.Asset;
import com.chaewookim.accountbookformoms.domain.transaction.dao.TransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionAccountRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionAmountRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionMemoRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionTitleUpdate;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.transaction.TransactionTypeRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.transaction.TransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.entity.Transaction;
import com.chaewookim.accountbookformoms.domain.transaction.enums.TransactionTypeEnum;
import com.chaewookim.accountbookformoms.domain.user.dao.UserRepository;
import com.chaewookim.accountbookformoms.domain.user.domain.User;
import com.chaewookim.accountbookformoms.global.error.CustomException;
import com.chaewookim.accountbookformoms.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse createTransaction(TransactionRequest request, Long userId) {
        Transaction transaction = Transaction.from(request, userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Asset asset = assetRepository.findByIdAndUsername(request.assetId(), user.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.ASSET_NOT_FOUND));

        setBalance(request.type(), asset, request.amount());

        transactionRepository.save(transaction);

        return TransactionResponse.from(transaction);
    }

    public List<TransactionResponse> getAllTransactions(Long userId) {
        return Transaction.from(transactionRepository.findAllByUserId(userId));
    }

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse updateTransactionTitle(Long id, TransactionTitleUpdate requestTitle, Long userId) {
        return TransactionResponse.from(getTransactionByIdAndUserId(id, userId).updateTitle(requestTitle.title()));
    }

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse updateTransactionMemo(Long id, TransactionMemoRequest requestMemo, Long userId) {
        return TransactionResponse.from(getTransactionByIdAndUserId(id, userId).updateMemo(requestMemo.memo()));
    }

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse updateTransactionAccount(Long id, TransactionAccountRequest requestAccount, Long userId) {
        return TransactionResponse.from(getTransactionByIdAndUserId(id, userId).updateAsset(requestAccount.assetId()));
    }

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse updateTransactionAmount(Long id, TransactionAmountRequest requestAmount, Long userId) {
        Asset asset = assetRepository.findByIdAndUsername(
                requestAmount.assetId(),
                userRepository.findById(userId)
                        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND))
                        .getUsername()
        ).orElseThrow(() -> new CustomException(ErrorCode.ASSET_NOT_FOUND));

        setBalance(requestAmount.type(), asset, requestAmount.amount());

        return TransactionResponse.from(getTransactionByIdAndUserId(id, userId).updateAmount(requestAmount.amount()));
    }

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse updateTransactionType(Long id, TransactionTypeRequest requestType, Long userId) {
        return TransactionResponse.from(getTransactionByIdAndUserId(id, userId).updateType(requestType.type()));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTransaction(Long id, Long userId) {
        Long deletedRow = transactionRepository.deleteByIdAndUserId(id, userId);

        if (deletedRow == 0) {
            throw new CustomException(ErrorCode.TRANSACTION_NOT_FOUND);
        }
    }

    private Transaction getTransactionByIdAndUserId(Long id, Long userId) {
        return transactionRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.TRANSACTION_NOT_FOUND));
    }

    private void setBalance(TransactionTypeEnum type, Asset asset, BigDecimal amount) {
        switch (type) {
            case EXPENSE:
                asset.subtractBalance(amount);
                break;
            case INCOME:
                asset.plusBalance(amount);
                break;
            default:
                throw new CustomException(ErrorCode.AMOUNT_NOT_FOUND);
        }
    }
}
