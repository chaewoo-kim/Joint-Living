package com.chaewookim.accountbookformoms.domain.asset.dao;

import com.chaewookim.accountbookformoms.domain.asset.domain.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
