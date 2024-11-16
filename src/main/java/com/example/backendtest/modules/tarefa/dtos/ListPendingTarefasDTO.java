package com.example.backendtest.modules.tarefa.dtos;

import com.example.backendtest.modules.tarefa.infra.entities.Tarefa;

import java.util.Date;

public record ListPendingTarefasDTO(int id, String titulo, String descricao,
                                    Date prazo, String departamento,
                                    String duracao, Date dataFinalizacao) {

    public ListPendingTarefasDTO(Tarefa tarefa) {
        this(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrazo(),
                tarefa.getDepartamento(),
                tarefa.getDuracao() + " horas",
                tarefa.getDataFinalizacao()
        );
    }
}
