package com.aquiles.helpdesk.domain;

import com.aquiles.helpdesk.domain.enums.Perfil;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class Pessoa {
    
    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String senha;
    protected HashSet<Integer> perfis = new HashSet<>();
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa(){
        super();
        addPerfis(Perfil.CLIENTE);

    }

    public Pessoa(Integer id, String nome, String cpf, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        addPerfis(Perfil.CLIENTE);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfil) {
        this.perfis.add(perfil.getCod());
    }
}
