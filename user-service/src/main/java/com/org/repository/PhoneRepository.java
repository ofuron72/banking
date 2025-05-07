package com.org.repository;

import com.org.entities.PhoneEntity;
import com.org.entities.PhoneEntity_;
import com.org.entities.UserEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PhoneRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public void save(PhoneEntity phone){
        entityManager.persist(phone);
    }

    public List<PhoneEntity> getAllPhonesByUserId(UUID userId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<PhoneEntity> criteria = cb.createQuery(PhoneEntity.class);

        Root<PhoneEntity> root = criteria.from(PhoneEntity.class);

        criteria.select(root)
                .where(cb.equal(root
                        .get(PhoneEntity_.user)
                        .get(UserEntity_.id), userId));

        return entityManager.createQuery(criteria).getResultList();
    }

    public List<PhoneEntity> getAllPhones() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<PhoneEntity> criteria = cb.createQuery(PhoneEntity.class);

        Root<PhoneEntity> root = criteria.from(PhoneEntity.class);

        criteria.select(root);

        return entityManager.createQuery(criteria).getResultList();
    }

    public void addPhone(PhoneEntity phone){
       entityManager.persist(phone);
    }

    public PhoneEntity getPhoneByNumber(String number){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PhoneEntity> criteria = cb.createQuery(PhoneEntity.class);
        Root<PhoneEntity> root = criteria.from(PhoneEntity.class);
        criteria.select(root).where(cb.equal(root.get(PhoneEntity_.number), number));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public void deletePhoneByNumber(String number){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<PhoneEntity> delete = cb.createCriteriaDelete(PhoneEntity.class);
        Root<PhoneEntity> root = delete.from(PhoneEntity.class);

        delete.where(cb.equal(root.get(PhoneEntity_.number), number));
        entityManager.createQuery(delete).executeUpdate();
    }

}
