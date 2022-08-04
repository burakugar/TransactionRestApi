package com.repository;

import com.model.entity.TransactionDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDataRepository extends JpaRepository<TransactionDataEntity, Integer> {

}
