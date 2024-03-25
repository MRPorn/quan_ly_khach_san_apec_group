package com.example.social_login.repository;

import com.example.social_login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = """
            select * from "user" where email = :email""", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

    @Query(value = """
            insert into "user" (email, password, role)
            values (:email, :password, :role)
            returning id, role, email, password;""", nativeQuery = true)
    UserEntity saveRegisterUser(String email,
                          String password,
                          String role
                          );
}
