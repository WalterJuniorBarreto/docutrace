package com.docutrace.identity.infrastructure.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import com.docutrace.identity.domain.UserAccount;
import com.docutrace.shared.config.BaseIntegrationTest;
import com.docutrace.shared.security.TenantContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;


class  JpaUserAccountRepositoryTest extends BaseIntegrationTest {

    @Autowired
    private JpaUserAccountRepository repository;

    private final UUID testOrganizationId = UUID.randomUUID();

    @BeforeEach
    void setUp(){
        TenantContext.setCurrentTenant(testOrganizationId);
    }

    @AfterEach
    void tearDown(){
        TenantContext.clear();
        repository.deleteAll();
    }

    @Test
    @DisplayName("Debe persistir el usuario inyectando el organization_id automaticamente")
    void shouldSaveUserAccountWithTenantInjected(){
        UUID userId = UUID.randomUUID();
        UserAccount user = new UserAccount(userId, "test@empresa.com", "hash_seguro");

        UserAccount savedUser = repository.saveAndFlush(user);

        assertThat(savedUser.getOrganizationId()).isEqualTo(testOrganizationId);
        assertThat(savedUser.getId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("Debe econtrar el usuario por correo electronico")
    void shouldFindUserByEmail(){
        UserAccount user = new UserAccount(UUID.randomUUID(), "findme@empresa.com", "hash");
        repository.saveAndFlush(user);

        Optional<UserAccount> found = repository.findByEmail("findme@empresa.com");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("findme@empresa.com");
    }
}
