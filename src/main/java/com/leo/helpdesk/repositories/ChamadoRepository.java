package com.leo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
    
}