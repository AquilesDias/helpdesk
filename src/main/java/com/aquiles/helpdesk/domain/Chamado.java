package com.aquiles.helpdesk.domain;

import com.aquiles.helpdesk.domain.enums.Prioridade;
import com.aquiles.helpdesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
public class Chamado implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataAbertura;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataFechamento;
    private Prioridade prioridade;
    private Status status;
    private String titulo;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    public Chamado(Integer id, Prioridade prioridade, Status status, String titulo, String observacoes, Cliente cliente, Tecnico tecnico) {
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.cliente = cliente;
        this.tecnico = tecnico;
    }
}
