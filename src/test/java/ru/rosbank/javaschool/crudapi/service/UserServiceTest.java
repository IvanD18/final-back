package ru.rosbank.javaschool.crudapi.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rosbank.javaschool.crudapi.entity.UserEntity;
import ru.rosbank.javaschool.crudapi.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserServiceTest {

    @Test
    void loadUserByUsernameShouldWork() {
        UserEntity user = new UserEntity();
        user.setUsername("Vasya");
        UserRepository repository = mock(UserRepository.class);
        PasswordEncoder encoder =  mock(PasswordEncoder.class);
        final UserService service= new UserService(repository,encoder);
        Mockito.when(repository.findByUsername("Vasya")).thenReturn(Optional.of(user));
        assertEquals( user.getUsername(), service.loadUserByUsername("Vasya").getUsername());

    }


}