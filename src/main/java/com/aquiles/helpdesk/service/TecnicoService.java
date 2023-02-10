package com.aquiles.helpdesk.service;

import com.aquiles.helpdesk.domain.DTO.TecnicoDTO;
import com.aquiles.helpdesk.domain.Pessoa;
import com.aquiles.helpdesk.domain.Tecnico;
import com.aquiles.helpdesk.repositories.PessoaRepository;
import com.aquiles.helpdesk.repositories.TecnicoRepository;
import com.aquiles.helpdesk.service.exception.DataIntegrityViolationException;
import com.aquiles.helpdesk.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    TecnicoRepository tecnicoRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Id não encontrado: " +id));
    }

    public List<Tecnico> findAll() {
       return tecnicoRepository.findAll();
    }

    public Tecnico save(TecnicoDTO tecnicoDTO) {
      tecnicoDTO.setId(null);
      validarCpfeEmail(tecnicoDTO);
      Tecnico tecnico = new Tecnico(tecnicoDTO);
      return tecnicoRepository.save(tecnico);
    }

    public Tecnico update(Integer id, TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico oldTecnico = findById(id);
        validarCpfeEmail(tecnicoDTO);
        oldTecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(oldTecnico);
    }

    public void delete(Integer id) {

        Tecnico tecnico = findById(id);

        if(tecnico.getChamados().size() > 0){
            throw new DataIntegrityViolationException("Não foi possivel deletar tecnico, verifique os chamados.");
        }

        tecnicoRepository.deleteById(id);
    }

    private void validarCpfeEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());

            if(pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()){
                throw new DataIntegrityViolationException("CPF já cadastrado!");
            }

            pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
            if(pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()){
                throw new DataIntegrityViolationException("E-mail já cadastrado!");
            }

    }
}
