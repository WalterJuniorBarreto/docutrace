package com.docutrace.identity.domain;

import com.docutrace.shared.domain.BaseTenantEntity;
import com.docutrace.shared.domain.TenantListener;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_account")
@EntityListeners(TenantListener.class)
public class UserAccount extends BaseTenantEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;


    @Column(name = "email", nullable = false, unique = true)
    private  String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    protected UserAccount(){}

    public UserAccount(UUID id, String email, String passwordHash){
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}