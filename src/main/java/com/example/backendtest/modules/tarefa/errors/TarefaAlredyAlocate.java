package com.example.backendtest.modules.tarefa.errors;

import com.example.backendtest.errors.BadRequest;

public class TarefaAlredyAlocate extends BadRequest {
    public TarefaAlredyAlocate() {
        super("Tarefa já foi alocada");
    }
}
