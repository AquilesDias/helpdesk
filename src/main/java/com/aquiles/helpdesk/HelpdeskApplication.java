package com.aquiles.helpdesk;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	TecnicoRepository tecnicoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico tecnico = new Tecnico(null, "Valdir Cezar", "19167655050", "valdir@gmail.com", "1234");
		tecnico.addPerfis(Perfil.ADMIN);

		Cliente cliente = new Cliente(null, "Linus Torvalds", "41940655064", "linus@mail.com", "123");

		Chamado chamado = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "Primeiro Chamado",cliente , tecnico);

		tecnicoRepository.saveAll(Arrays.asList(tecnico));
		clienteRepository.saveAll(Arrays.asList(cliente));
		chamadoRepository.saveAll(Arrays.asList(chamado));

	}
}
