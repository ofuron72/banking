package com.org.repository;

import com.org.entities.UserEntity;
import com.org.entities.UserEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager entityManager;

    public UUID saveUser(UserEntity user) {
        entityManager.persist(user);
        return user.getId();
    }

    public UserEntity getUserById(UUID id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);

        Root<UserEntity> root = cq.from(UserEntity.class);

        cq.where(cb.equal(root.get(UserEntity_.id), id));

        return entityManager.createQuery(cq).getSingleResult();
    }


}
