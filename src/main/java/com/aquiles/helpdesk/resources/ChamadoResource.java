package com.aquiles.helpdesk.resources;

import com.aquiles.helpdesk.domain.Chamado;
import com.aquiles.helpdesk.domain.DTO.ChamadoDTO;
import com.aquiles.helpdesk.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    /*****    REQUISIÇÕES GET     *****/

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
       List<Chamado> chamado = chamadoService.findAll();
       List<ChamadoDTO> chamadoDTOS = chamado.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
       return ResponseEntity.ok().body(chamadoDTOS);
    }

    /*****    REQUISIÇÕES POST     *****/
    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO){
        Chamado chamado = chamadoService.create(chamadoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /*****    REQUISIÇÕES PUT     *****/

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO chamadoDTO){
        Chamado chamado = chamadoService.update(id, chamadoDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}
