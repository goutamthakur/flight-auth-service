package com.goutamthakur.flight.auth.infrastructure.persistence.jpa;

import com.goutamthakur.flight.auth.infrastructure.persistence.entity.SessionEntity;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SessionJpaRepository extends JpaRepository<SessionEntity, Long> {
  Optional<SessionEntity> findByRefreshTokenHashAndIsActiveTrue(String refreshTokenHash);

  @Modifying
  @Query(
      """
        UPDATE SessionEntity s
        SET s.accessTokenJti = :accessTokenJti,
            s.lastActiveAt = :lastActiveAt
        WHERE s.refreshTokenHash = :refreshTokenHash
    """)
  int updateAccessTokenJti(
      String refreshTokenHash, String accessTokenJti, Instant lastActiveAt);
}
