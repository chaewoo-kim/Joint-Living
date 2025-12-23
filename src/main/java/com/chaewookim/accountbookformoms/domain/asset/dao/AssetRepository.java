package com.chaewookim.accountbookformoms.domain.asset.dao;

import com.chaewookim.accountbookformoms.domain.asset.domain.Asset;
import com.chaewookim.accountbookformoms.domain.asset.dto.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    void deleteByAccountNumber(String accountNumber);

    Optional<Asset> findByAccountNumber(String accountNumber);

    List<Asset> findAllByUsername(String username);

    Optional<Asset> findByIdAndUsername(Long id, String username);
}
