package com.aquiles.helpdesk.service;

import com.aquiles.helpdesk.domain.Cliente;
import com.aquiles.helpdesk.domain.DTO.ClienteDTO;
import com.aquiles.helpdesk.domain.Pessoa;
import com.aquiles.helpdesk.repositories.ClienteRepository;
import com.aquiles.helpdesk.repositories.PessoaRepository;
import com.aquiles.helpdesk.service.exception.DataIntegrityViolationException;
import com.aquiles.helpdesk.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado: " +id));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente save(ClienteDTO clienteDTO){
        clienteDTO.setId(null);
        validarCpfeEmail(clienteDTO);
        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public Cliente update(Integer id, ClienteDTO clienteDTO){
        clienteDTO.setId(id);
        Cliente oldCliente = findById(id);
        validarCpfeEmail(clienteDTO);
        oldCliente = new Cliente(clienteDTO);
        return clienteRepository.save(oldCliente);
    }

    public void delete(Integer id){
        Cliente cliente = findById(id);

        if(cliente.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Não foi possivel deletar cliente, verificar chamados!");
        }

        clienteRepository.deleteById(id);
    }

    private void validarCpfeEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());

        if(pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }

        pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if(pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado!");
        }

    }
}
