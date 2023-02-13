package com.aquiles.helpdesk.resources;

import com.aquiles.helpdesk.domain.Cliente;
import com.aquiles.helpdesk.domain.DTO.ClienteDTO;
import com.aquiles.helpdesk.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    /*****    REQUISIÇÕES GET     *****/

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDTO> clienteDTOS = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clienteDTOS);
    }

    /*****    REQUISIÇÕES POST     *****/

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.save(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    /*****    REQUISIÇÕES UPDATE     *****/

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.update(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    /*****    REQUISIÇÕES DELETE     *****/

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
