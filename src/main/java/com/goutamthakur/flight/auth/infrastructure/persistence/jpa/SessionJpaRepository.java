package com.goutamthakur.flight.auth.infrastructure.persistence.jpa;

import com.goutamthakur.flight.auth.infrastructure.persistence.entity.SessionEntity;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
      @Param("refreshTokenHash") String refreshTokenHash,
      @Param("accessTokenJti") String accessTokenJti,
      @Param("lastActiveAt") Instant lastActiveAt);

  @Modifying
  @Query(
      """
        UPDATE SessionEntity s
        SET s.isActive = false
        WHERE s.refreshTokenHash = :refreshTokenHash
    """)
  int markSessionInactive(@Param("refreshTokenHash") String refreshTokenHash);
}
