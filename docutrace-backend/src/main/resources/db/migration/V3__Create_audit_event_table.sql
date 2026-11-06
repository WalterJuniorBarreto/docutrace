CREATE TABLE audit_event (
    id RAW(16) DEFAULT SYS_GUID() PRIMARY KEY,
    organization_id RAW(16) NOT NULL,
    actor_id RAW(16),
    action VARCHAR2(100) NOT NULL,
    resource_type VARCHAR2(100) NOT NULL,
    resource_id VARCHAR2(255),
    result VARCHAR2(50) NOT NULL,
    details CLOB,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT fk_audit_organization FOREIGN KEY (organization_id) REFERENCES organization(id)
);

CREATE INDEX idx_audit_org_date ON audit_event(organization_id, created_at DES);
CREATE INDEX idx_audit_actor ON audit_event(actor_id);
CREATE INDEX idx_audit_action ON audit_event(action);