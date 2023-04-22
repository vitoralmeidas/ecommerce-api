package com.vaos.store.api.repository;

import com.vaos.store.api.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
