package com.chaewookim.accountbookformoms.domain.transaction.application;

import com.chaewookim.accountbookformoms.domain.transaction.dao.TransactionRepository;
import com.chaewookim.accountbookformoms.domain.transaction.dto.request.TransactionRequest;
import com.chaewookim.accountbookformoms.domain.transaction.dto.response.TransactionResponse;
import com.chaewookim.accountbookformoms.domain.transaction.entity.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse createTransaction(TransactionRequest request, Long userId) {
        Transaction transaction = Transaction.from(request, userId);

        transactionRepository.save(transaction);

        return TransactionResponse.from(transaction);
    }
}
