package com.aquiles.helpdesk.service;

import com.aquiles.helpdesk.domain.Chamado;
import com.aquiles.helpdesk.repositories.ChamadoRepository;
import com.aquiles.helpdesk.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! ID: " +id));
    }

    public List<Chamado> findAll(){
        return chamadoRepository.findAll();
    }
}
