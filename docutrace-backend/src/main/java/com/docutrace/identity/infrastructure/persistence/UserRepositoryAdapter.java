package com.docutrace.identity.infrastructure.persistence;

import com.docutrace.identity.domain.UserAccount;
import com.docutrace.identity.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final JpaUserAccountRepository jpaRepository;

    @Override
    public UserAccount save(UserAccount user){
        return jpaRepository.save(user);
    }

    @Override
    public Optional<UserAccount> findByEmail(String email){
        return jpaRepository.findByEmail(email);
    }

    @Override
    public Optional<UserAccount> findById(UUID id){
        return jpaRepository.findById(id);
    }
}
