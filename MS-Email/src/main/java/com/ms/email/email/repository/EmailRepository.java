package com.ms.email.email.repository;

import com.ms.email.email.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailEntity, UUID> {
    Optional<EmailEntity> findByUserId(UUID uuid);
}
