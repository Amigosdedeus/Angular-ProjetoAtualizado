package com.leo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    
}
