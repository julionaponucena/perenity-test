package com.example.backendtest.modules.tarefa.errors;

import com.example.backendtest.errors.EntityNotFound;

public class TarefaNotFound extends EntityNotFound {
    public TarefaNotFound() {
        super("Tarefa não encontrada");
    }
}
