package com.aquiles.helpdesk.domain.DTO;

import com.aquiles.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class ChamadoDTO implements Serializable {

    private static final long serialVersionUID = 1l;

    protected Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataAbertura;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataFechamento;

    @NotNull(message = "Campo PRIORIDADE requirido!")
    protected Integer prioridade;

    @NotNull(message = "Campo STATUS requerido!")
    protected Integer status;

    @NotNull(message = "Campo TITULO requerido!")
    protected String titulo;

    @NotNull(message = "Campo OBSERVACOES requerido!")
    protected String observacoes;

    @NotNull(message = "Campo TECNICO requerido!")
    private Integer tecnico;

    @NotNull(message = "Campo CLIENTE requerido!")
    private Integer cliente;

    private String nomeTecnico;

    private String nomeCliente;

    public ChamadoDTO(Chamado chamado) {
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade().getCod();
        this.status = chamado.getStatus().getCod();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.cliente = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.nomeCliente = chamado.getCliente().getNome();;
    }
}
