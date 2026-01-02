package com.chaewookim.accountbookformoms.domain.category.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CategoryEnum {
    FOOD("FOOD", "외식"),
    TRANSPORTATION("TRANSPORTATION", "교통비"),
    ENTERTAINMENT("ENTERTAINMENT", "문화생활"),
    HOUSEHOLD_GOODS("HOUSEHOLD_GOODS", "생필품"),
    CLOTHING("CLOTHING", "의류"),
    BEAUTY("BEAUTY", "미용"),
    MEDICAL("MEDICAL", "의료"),
    HEALTHCARE("HEALTHCARE", "건강"),
    EDUCATION("EDUCATION", "교육"),
    COMMUNICATION("COMMUNICATION", "통신비"),
    SUBSCRIPTION_FEE("SUBSCRIPTION_FEE", "구독비"),
    GIFT_CEREMONY("GIFT_CEREMONY", "경조사비"),
    SAVINGS("SAVINGS", "저축"),
    APPLIANCES("APPLIANCES", "가전"),
    UTILITIES("UTILITIES", "공과금"),
    EQUIPMENT("EQUIPMENT", "장비"),
    TRAVEL("여행", "TRAVEL"),
    BEVERAGE("BEVERAGE", "음료"),
    ALCOHOL("ALCOHOL", "술");

    private String key;
    private String value;
}
