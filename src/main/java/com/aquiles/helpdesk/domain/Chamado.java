package com.aquiles.helpdesk.domain;

import com.aquiles.helpdesk.domain.enums.Prioridade;
import com.aquiles.helpdesk.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Chamado {

    private Integer id;
    private Date dataAbertura;
    private Date dataFechamento;
    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;

    private Cliente cliente;
    private Tecnico tecnico;
}
