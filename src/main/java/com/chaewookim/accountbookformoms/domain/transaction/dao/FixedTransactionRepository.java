package com.chaewookim.accountbookformoms.domain.transaction.dao;

import com.chaewookim.accountbookformoms.domain.transaction.entity.FixedTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FixedTransactionRepository extends JpaRepository<FixedTransaction, Long> {
    List<FixedTransaction> findAllByUserId(Long userId);

    Optional<FixedTransaction> findByIdAndUserId(Long id, Long userId);

    Long deleteByIdAndUserId(Long id, Long userId);
}
