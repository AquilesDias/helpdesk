package com.aquiles.helpdesk.service;

import com.aquiles.helpdesk.domain.Chamado;
import com.aquiles.helpdesk.domain.Cliente;
import com.aquiles.helpdesk.domain.DTO.ChamadoDTO;
import com.aquiles.helpdesk.domain.Tecnico;
import com.aquiles.helpdesk.domain.enums.Prioridade;
import com.aquiles.helpdesk.domain.enums.Status;
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

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! ID: " +id));
    }

    public List<Chamado> findAll(){
        return chamadoRepository.findAll();
    }

   public Chamado create(ChamadoDTO chamadoDTO){
        return chamadoRepository.save(newChamado(chamadoDTO));
   }

   public Chamado newChamado(ChamadoDTO chamadoDTO){
       Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
       Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

       Chamado chamado = new Chamado();

       if(chamadoDTO.getId() != null){
           chamado.setId(chamadoDTO.getId());
       }

       chamado.setCliente(cliente);
       chamado.setTecnico(tecnico);
       chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
       chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
       chamado.setTitulo(chamadoDTO.getTitulo());
       chamado.setObservacoes(chamadoDTO.getObservacoes());
       return chamado;
   }
}
