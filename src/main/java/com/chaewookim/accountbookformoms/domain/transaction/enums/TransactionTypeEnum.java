package com.chaewookim.accountbookformoms.domain.transaction.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TransactionTypeEnum {
    INCOME("INCOME", "수입"),
    EXPENSE("EXPENSE", "지출");

    private final String key;
    private final String value;
}
