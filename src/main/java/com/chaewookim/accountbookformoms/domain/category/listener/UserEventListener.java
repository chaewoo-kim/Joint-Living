package com.chaewookim.accountbookformoms.domain.category.listener;

import com.chaewookim.accountbookformoms.domain.category.application.CategoryService;
import com.chaewookim.accountbookformoms.global.event.UserSignedUpEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventListener {

    private final CategoryService categoryService;

    @EventListener
    @Transactional
    public void handleUserSignedUpEvent(UserSignedUpEvent event) {
        log.info("새 회원 가입: userId = {}", event.userId());

        categoryService.createDefaultCategory(event.userId());
    }
}
