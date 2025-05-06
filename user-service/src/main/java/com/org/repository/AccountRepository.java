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

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public void save(AccountEntity account){
        entityManager.persist(account);
    }

    public AccountEntity findById(UUID id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AccountEntity> cq = cb.createQuery(AccountEntity.class);

        Root<AccountEntity> root = cq.from(AccountEntity.class);

        cq.select(root).where(cb.equal(root.get(AccountEntity_.id),id));

        return entityManager.createQuery(cq).getSingleResult();
    }

    public AccountEntity findByUserId(UUID userId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AccountEntity> cq = cb.createQuery(AccountEntity.class);

        Root<AccountEntity> root = cq.from(AccountEntity.class);

        cq.select(root).where(cb.equal(root.get(AccountEntity_.user)
                .get(UserEntity_.id), userId ));

        return entityManager.createQuery(cq).getSingleResult();
    }

    public List<AccountEntity> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AccountEntity> criteria = cb.createQuery(AccountEntity.class);

        Root<AccountEntity> root = criteria.from(AccountEntity.class);

        criteria.select(root);

        return entityManager.createQuery(criteria).getResultList();
    }

    public List<AccountEntity> getAccountsByUserIds(List<UUID> userIds) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountEntity> cq = cb.createQuery(AccountEntity.class);
        Root<AccountEntity> accountRoot = cq.from(AccountEntity.class);

        Predicate predicate = accountRoot.get("userId").in(userIds);
        cq.where(predicate);

        return entityManager.createQuery(cq).getResultList();
    }


}
