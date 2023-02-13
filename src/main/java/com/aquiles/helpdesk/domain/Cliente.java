package com.aquiles.helpdesk.domain;

import com.aquiles.helpdesk.domain.DTO.ClienteDTO;
import com.aquiles.helpdesk.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@Entity
public class Cliente extends Pessoa {

    private static final long serialVersionUID = 1l;

    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(){
        super();
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis().stream().map(x -> x.getCod()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email,String senha) {
        super(id, nome, cpf, email, senha);
        addPerfis(Perfil.CLIENTE);
    }
}
