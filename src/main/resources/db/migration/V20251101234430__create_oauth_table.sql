-- Write your SQL migration here

CREATE TABLE oauth_accounts (
    id BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    provider VARCHAR(100) NOT NULL,
    provider_user_id VARCHAR(255) NOT NULL,
    provider_email VARCHAR(320),
    provider_raw json,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    UNIQUE KEY uq_provider_user (provider, provider_user_id),
    CONSTRAINT fk_oauth_accounts_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE

);
