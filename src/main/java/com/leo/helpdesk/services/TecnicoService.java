package com.leo.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.helpdesk.domain.Tecnico;
import com.leo.helpdesk.domain.dtos.TecnicoDTO;
import com.leo.helpdesk.repositories.TecnicoRepository;
import java.util.List;
import com.leo.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService{
    
    @Autowired
    private TecnicoRepository repository;

        public Tecnico findById(Integer id) {
           Optional<Tecnico> obj = repository.findById(id);
           return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " +id));
    
        }

        public List<Tecnico> findAll() {
           return repository.findAll();
        }

        public Tecnico create(TecnicoDTO objDTO) {
            objDTO.setId(null);
           Tecnico newObj = new Tecnico(objDTO);
           return repository.save(newObj);
        }
}