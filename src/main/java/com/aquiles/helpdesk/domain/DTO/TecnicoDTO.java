package com.aquiles.helpdesk.domain.DTO;

import com.aquiles.helpdesk.domain.Tecnico;
import com.aquiles.helpdesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = 1l;

    protected Integer id;

    @NotNull(message = "Campo nome requerido!")
    protected String nome;

    @NotNull(message = "Campo cpf requerido!")
    protected String cpf;

    @NotNull(message = "Campo email requerido!")
    protected String email;

    @NotNull(message = "Campo senha requerido!")
    protected String senha;

    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO(){
        super();
        addPerfis(Perfil.CLIENTE);
    }


    public TecnicoDTO(Tecnico obj) {
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

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfil) {
        this.perfis.add(perfil.getCod());
    }


}
