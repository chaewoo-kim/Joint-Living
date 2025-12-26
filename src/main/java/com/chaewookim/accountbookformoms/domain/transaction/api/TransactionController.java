package com.chaewookim.accountbookformoms.domain.transaction.api;

import com.chaewookim.accountbookformoms.domain.transaction.application.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


}
