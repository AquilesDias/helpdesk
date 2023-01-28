package com.aquiles.helpdesk.domain.enums;

import lombok.Getter;

@Getter
public enum Perfil {

    ADMIN (0, "ROLE_ADMIN"),
    CLIENTE(1, "ROLE_CLIENTE"),
    TECNICO(2, "ROLE_TECNICO");

    private int cod;
    private String descricao;

    Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static Perfil toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(Perfil x : Perfil.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Perfil Inválido.");

    }


}
