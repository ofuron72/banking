package com.org.repository;

import com.org.entities.UserEntity;
import com.org.objects.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CustomRepository extends JpaRepository<UserEntity, UUID>,
        JpaSpecificationExecutor<UserEntity> {

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

    @Query(value = """
    SELECT u.id AS id,
           u.full_name AS fullName,
           u.birth_date AS birthDate,
             array_agg(DISTINCT p.phone) AS number,
            array_agg(DISTINCT e.email) AS email,
           a.initial_deposit AS initialDeposit,
           a.balance AS balance
      FROM users u
      LEFT JOIN user_phones p ON p.user_id = u.id
      LEFT JOIN user_emails e ON e.user_id = u.id
      LEFT JOIN accounts a ON a.user_id = u.id
     WHERE (CAST(:birthDate AS date) IS NULL OR u.birth_date > :birthDate)
       AND (CAST(:fullName AS text) IS NULL OR LOWER(u.full_name) LIKE LOWER(CONCAT(:fullName, '%')))
       AND (CAST(:number AS text) IS NULL OR p.phone = :number)
       AND (CAST(:email AS text) IS NULL OR e.email = :email)
  GROUP BY u.id, u.full_name, u.birth_date, a.initial_deposit, a.balance""",
            countQuery = """
    SELECT COUNT(DISTINCT u.id)
      FROM users u
      LEFT JOIN user_phones p ON p.user_id = u.id
      LEFT JOIN user_emails e ON e.user_id = u.id
     WHERE (CAST(:birthDate AS date) IS NULL OR u.birth_date > :birthDate)
       AND (CAST(:fullName AS text) IS NULL OR LOWER(u.full_name) LIKE LOWER(CONCAT(:fullName, '%')))
       AND (CAST(:number AS text) IS NULL OR p.phone = :number)
       AND (CAST(:email AS text) IS NULL OR e.email = :email)
    """,
            nativeQuery = true)
    Page<UserProjection> findAllUsersWithFilter(
            @Param("birthDate") LocalDate birthDate,
            @Param("fullName") String fullName,
            @Param("number") String number,
            @Param("email") String email,
            Pageable pageable);
}