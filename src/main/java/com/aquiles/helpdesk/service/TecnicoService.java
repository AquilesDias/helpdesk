package com.aquiles.helpdesk.service;

import com.aquiles.helpdesk.domain.Tecnico;
import com.aquiles.helpdesk.repositories.TecnicoRepository;
import com.aquiles.helpdesk.service.exception.ObjectNotFoundException;
import org.hibernate.type.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado: " +id));
    }
}
