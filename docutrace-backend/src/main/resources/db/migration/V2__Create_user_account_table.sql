CREATE TABLE user_account (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    organization_id RAW(16) NOT NULL,
    email VARCHAR2(255) NOT NULL,
    password_hash VARCHAR2(255) NOT NULL,
    status VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT fk_user_organization FOREIGN KEY (organization_id) REFERENCES  organization(id),
    CONSTRAINT uq_user_email UNIQUE (email)

);

CREATE INDEX idx_user_organization ON user_account(organization_id);
