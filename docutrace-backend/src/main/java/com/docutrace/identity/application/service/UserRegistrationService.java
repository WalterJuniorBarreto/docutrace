package com.docutrace.identity.application.service;

import com.docutrace.identity.domain.UserAccount;
import com.docutrace.identity.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserAccount registerUser(String email, String rawPassword){
        if(userRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("El usuario ya existe en esta organizacion");
        }

        String hashedPassword = passwordEncoder.encode(rawPassword);
        UserAccount newUser = new UserAccount(UUID.randomUUID(), email, hashedPassword);
        return userRepository.save(newUser);
    }
}
