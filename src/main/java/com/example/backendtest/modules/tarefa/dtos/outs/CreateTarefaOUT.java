package com.example.backendtest.modules.tarefa.dtos.outs;

import com.example.backendtest.modules.tarefa.infra.entities.Tarefa;

import java.util.Date;

public record CreateTarefaOUT(int id, String titulo,
                              String departamento,
                              String descricao, Date prazo,
                              int duracao
                                ) {
    public CreateTarefaOUT(Tarefa tarefa) {
        this(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDepartamento(),
                tarefa.getDescricao(),
                tarefa.getPrazo(),
                tarefa.getDuracao()
        );
    }
}
