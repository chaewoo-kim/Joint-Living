package com.chaewookim.accountbookformoms.domain.category.dao;

import com.chaewookim.accountbookformoms.domain.category.domain.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
}
