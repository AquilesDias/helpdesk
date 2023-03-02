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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanceDB(){

        Tecnico tecnico = new Tecnico(null, "Naruto", "19167655052", "Naruto@gmail.com", encoder.encode("1234"));
        tecnico.addPerfis(Perfil.ADMIN);

        Tecnico tecnico2 = new Tecnico(null, "Sasuke", "19167655053", "Sasuke@gmail.com", encoder.encode("1234"));
        tecnico.addPerfis(Perfil.ADMIN);

        Tecnico tecnico3 = new Tecnico(null, "Hinata", "191676550504", "Hinata@gmail.com", encoder.encode("1324"));
        tecnico.addPerfis(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Itachi", "41940655064", "Itachi@mail.com", encoder.encode("1234"));
        Cliente cliente1 = new Cliente(null, "Konohamaru", "41940655764", "Konohamaru@mail.com", encoder.encode("1234"));

        Chamado chamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "Primeiro Chamado",cliente , tecnico);
        Chamado chamado1 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "chamado 02", "Segundo Chamado",cliente1 , tecnico2);

        tecnicoRepository.saveAll(Arrays.asList(tecnico,tecnico2, tecnico3));
        clienteRepository.saveAll(Arrays.asList(cliente, cliente1));
        chamadoRepository.saveAll(Arrays.asList(chamado,chamado1));
    }

}
