package com.teste.selaz.selaz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teste.selaz.selaz.entities.UserEntity;
import com.teste.selaz.selaz.repositories.UserRepository;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Usuário não encontrado!");

        return User.withUsername(username)
                .password(username)
                .authorities(user.getNivel())
                .build();
    }
}
