package com.aquiles.helpdesk.resources;

import com.aquiles.helpdesk.domain.DTO.TecnicoDTO;
import com.aquiles.helpdesk.domain.Tecnico;
import com.aquiles.helpdesk.repositories.TecnicoRepository;
import com.aquiles.helpdesk.service.TecnicoService;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;
    private TecnicoRepository tecnicoRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico obj =  tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> tecnicos = tecnicoService.findAll();
        List<TecnicoDTO> tecnicoDTOS = tecnicos.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(tecnicoDTOS);
    }
}
