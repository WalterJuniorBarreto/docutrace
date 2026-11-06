CREATE TABLE organization (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    legal_name VARCHAR2(255) NOT NULL,
    commercial_name VARCHAR2(255) NOT NULL,
    tax_id VARCHAR2(50),
    status VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX idx_organization_status ON organization(status);