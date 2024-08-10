package com.leo.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
