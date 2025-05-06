package com.org.repository;

import com.org.entities.EmailEntity;
import com.org.entities.EmailEntity_;
import com.org.entities.UserEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EmailRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    public void save(EmailEntity email){
        entityManager.persist(email);
    }

    public EmailEntity findByEmail(String email){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<EmailEntity> cq = cb.createQuery(EmailEntity.class);

        Root<EmailEntity> root = cq.from(EmailEntity.class);

        cq.select(root).where(cb.equal(root.get(EmailEntity_.email), email));

        return entityManager.createQuery(cq).getSingleResult();
    }

    public EmailEntity findByUserId(UUID userId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<EmailEntity> cq = cb.createQuery(EmailEntity.class);

        Root<EmailEntity> root = cq.from(EmailEntity.class);

        cq.select(root).where(cb.equal(root.get(EmailEntity_.user)
                .get(UserEntity_.id), userId));

        return entityManager.createQuery(cq).getSingleResult();
    }

    public List<EmailEntity> findAllByUserId(UUID userId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<EmailEntity> criteria = cb.createQuery(EmailEntity.class);

        Root<EmailEntity> root = criteria.from(EmailEntity.class);

        criteria.select(root)
                .where(cb.equal(root
                        .get(EmailEntity_.user)
                        .get(UserEntity_.id), userId));

        return entityManager.createQuery(criteria).getResultList();
    }

    public List<EmailEntity> findAll(){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<EmailEntity> criteria = cb.createQuery(EmailEntity.class);

        Root<EmailEntity> root = criteria.from(EmailEntity.class);

        criteria.select(root);

        return entityManager.createQuery(criteria).getResultList();
    }
}
