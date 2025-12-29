package com.chaewookim.accountbookformoms.domain.transaction.dao;

import com.chaewookim.accountbookformoms.domain.transaction.entity.FixedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedTransactionRepository extends JpaRepository<FixedTransaction, Long> {
}
