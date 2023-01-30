package com.aquiles.helpdesk.domain;

import com.aquiles.helpdesk.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
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

    public Cliente(Integer id, String nome, String cpf, String email,String senha) {
        super(id, nome, cpf, email, senha);
        addPerfis(Perfil.CLIENTE);
    }
}
