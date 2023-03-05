package com.aquiles.helpdesk.resources;

import com.aquiles.helpdesk.domain.DTO.TecnicoDTO;
import com.aquiles.helpdesk.domain.Tecnico;
import com.aquiles.helpdesk.repositories.TecnicoRepository;
import com.aquiles.helpdesk.service.TecnicoService;
import org.apache.coyote.Response;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.ServerSocket;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    /*****    REQUISIÇÕES GET     *****/
    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico obj =  tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> tecnicos = tecnicoService.findAll();
        List<TecnicoDTO> tecnicoDTOS = tecnicos.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(tecnicoDTOS);
    }

    /*****    REQUISIÇÕES POST     *****/
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico tecnico = tecnicoService.save(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).body(tecnico);
    }

    /*****    REQUISIÇÕES PUT     *****/

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @RequestBody TecnicoDTO tecnicoDTO){
        Tecnico tecnico = tecnicoService.update(id,tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));

    }

    /*****    REQUISIÇÕES DELETE     *****/

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
