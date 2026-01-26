package com.chaewookim.accountbookformoms.domain.category.dao;

import com.chaewookim.accountbookformoms.domain.category.domain.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    List<UserCategory> findAllByUserId(Long userId);
}
