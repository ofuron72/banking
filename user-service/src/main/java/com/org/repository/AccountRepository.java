package com.org.repository;

import com.org.entities.AccountEntity;
import com.org.entities.AccountEntity_;
import com.org.entities.UserEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
@Repository
@RequiredArgsConstructor
public class AccountRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional
    public void save(AccountEntity account){
        entityManager.persist(account);
    }

    public Optional<AccountEntity> findByUserId(UUID userId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AccountEntity> cq = cb.createQuery(AccountEntity.class);

        Root<AccountEntity> root = cq.from(AccountEntity.class);

        cq.select(root).where(cb.equal(root.get(AccountEntity_.user)
                .get(UserEntity_.id), userId ));

        return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
    }

    @Transactional
    public Boolean decreaseBalance(UUID userId, BigDecimal amount) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        var criteriaUpdate = criteriaBuilder
                .createCriteriaUpdate(AccountEntity.class);

        var root = criteriaUpdate.from(AccountEntity.class);

        Predicate condition = criteriaBuilder.greaterThanOrEqualTo(root
                .get(AccountEntity_.balance), amount);

        Predicate correctUser = criteriaBuilder.equal(root.get(AccountEntity_.user)
                .get(UserEntity_.id), userId );

        criteriaUpdate.set(root.get(AccountEntity_.balance),
                        criteriaBuilder.diff(root.get(AccountEntity_.balance),
                                criteriaBuilder.literal(amount)))
                .where(
                        criteriaBuilder.and(
                                correctUser,
                                condition)
                );

        int updateRows = entityManager.createQuery(criteriaUpdate).executeUpdate();

        return updateRows == 1;
    }

    @Transactional
    public void increaseBalance(UUID userId, BigDecimal amount) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        var criteriaUpdate = criteriaBuilder.createCriteriaUpdate(AccountEntity.class);

        var root = criteriaUpdate.from(AccountEntity.class);

        criteriaUpdate
                .set(root.get(AccountEntity_.balance),
                        criteriaBuilder.sum(root.get(AccountEntity_.balance),
                                criteriaBuilder.literal(amount)))
                .where(criteriaBuilder.equal((root.get(AccountEntity_.user)
                        .get(UserEntity_.id)), userId));

        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }


}
