package com.org.repository;

import com.org.entities.TransactionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class TransactionRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public TransactionEntity save(TransactionEntity transactionEntity) {
        entityManager.persist(transactionEntity);

        return transactionEntity;
    }
}
