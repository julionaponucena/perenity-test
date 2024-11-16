package com.example.backendtest.modules.tarefa.errors;

import com.example.backendtest.errors.BadRequest;

public class TarefaNotAlocate extends BadRequest {
    public TarefaNotAlocate() {
        super("Não se pode finalizar uma tarefa que ainda não foi alocada");
    }
}
