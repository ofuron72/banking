package com.org.repository;

import com.org.entities.UserEntity;
import com.org.objects.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CustomRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = """
SELECT u.id AS id,
    u.full_name AS fullName,
    u.birth_date AS birthDate,
    array_agg(DISTINCT p.phone) AS number,
    array_agg(DISTINCT e.email) AS email,
    a.initial_deposit AS initialDeposit,
    a.balance AS balance
    FROM users u
LEFT JOIN user_phones p ON u.id = p.user_id
LEFT JOIN user_emails e ON u.id = e.user_id
LEFT JOIN accounts a ON u.id = a.user_id
    group by u.id, u.full_name, u.birth_date, a.initial_deposit, a.balance""",
            nativeQuery = true)
    List<UserProjection> getAllUserProjections();
}
