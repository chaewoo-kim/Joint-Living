package com.chaewookim.accountbookformoms.domain.transaction.dao;

import com.chaewookim.accountbookformoms.domain.transaction.entity.FixedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixedTransactionRepository extends JpaRepository<FixedTransaction, Long> {
    List<FixedTransaction> findAllByUserId(Long userId);
}
