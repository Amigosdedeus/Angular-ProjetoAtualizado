package com.leo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    
}