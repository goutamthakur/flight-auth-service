package com.goutamthakur.flight.auth.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "sessions",
        indexes = {
                @Index(name = "idx_sessions_active", columnList = "user_id, is_active"),
                @Index(name = "idx_sessions_refresh_token", columnList = "refresh_token_hash"),
                @Index(name = "idx_sessions_access_jti", columnList = "access_token_jti")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_sessions_user")
    )
    private UserEntity user;

    @Column(name = "device_id", length = 255)
    private String deviceId;

    @Column(name = "device_info", length = 1024)
    private String deviceInfo;

    @Column(name = "ip_address", length = 100)
    private String ipAddress;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "access_token_jti", nullable = false, length = 255)
    private String accessTokenJti;

    @Column(name = "refresh_token_hash", nullable = false, length = 255)
    private String refreshTokenHash;

    @Column(name = "refresh_token_expiry", nullable = false)
    private Instant refreshTokenExpiry;

    @Column(name = "last_active_at", nullable = false)
    private Instant lastActiveAt;

    @Column(name = "revoked_at")
    private Instant revokedAt;

    @Column(name = "revoked_reason", length = 255)
    private String revokedReason;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}

