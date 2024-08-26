package com.leo.helpdesk.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leo.helpdesk.domain.Pessoa;
import com.leo.helpdesk.repositories.PessoaRepository;
import com.leo.helpdesk.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private PessoaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Procurando usuário por email: {}", email);
        Optional<Pessoa> user = repository.findByEmail(email);
        if (user.isPresent()) {
            logger.info("Usuário encontrado: {}", user.get().getEmail());
            return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfis());
        }
        logger.warn("Usuário não encontrado: {}", email);
        throw new UsernameNotFoundException(email);
    }
}
