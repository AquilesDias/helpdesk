package com.aquiles.helpdesk.service;

import com.aquiles.helpdesk.domain.Chamado;
import com.aquiles.helpdesk.domain.Cliente;
import com.aquiles.helpdesk.domain.Tecnico;
import com.aquiles.helpdesk.domain.enums.Perfil;
import com.aquiles.helpdesk.domain.enums.Prioridade;
import com.aquiles.helpdesk.domain.enums.Status;
import com.aquiles.helpdesk.repositories.ChamadoRepository;
import com.aquiles.helpdesk.repositories.ClienteRepository;
import com.aquiles.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    TecnicoRepository tecnicoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ChamadoRepository chamadoRepository;

    public void instanceDB(){

        Tecnico tecnico = new Tecnico(null, "Valdir Cezar", "19167655050", "valdir@gmail.com", "1234");
        tecnico.addPerfis(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Linus Torvalds", "41940655064", "linus@mail.com", "123");

        Chamado chamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "Primeiro Chamado",cliente , tecnico);

        tecnicoRepository.saveAll(Arrays.asList(tecnico));
        clienteRepository.saveAll(Arrays.asList(cliente));
        chamadoRepository.saveAll(Arrays.asList(chamado));
    }

}
