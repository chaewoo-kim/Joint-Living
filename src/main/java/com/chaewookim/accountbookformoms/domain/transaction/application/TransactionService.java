package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.transaction.dao.TransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.TransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.entity.Transaction;
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
        return Transaction.from(transactionRepository.getAllByUserId(userId));
    }
}
