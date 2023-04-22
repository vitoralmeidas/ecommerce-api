package com.vaos.store.api.repository;

import com.vaos.store.api.entity.User;
import com.vaos.store.api.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends
        JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    
}