package com.docutrace.identity.domain.repository;

import com.docutrace.identity.domain.UserAccount;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    UserAccount save(UserAccount user);
    Optional<UserAccount> findByEmail(String email);
    Optional<UserAccount> findById(UUID id);
}
