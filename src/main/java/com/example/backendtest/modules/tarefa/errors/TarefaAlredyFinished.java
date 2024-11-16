package com.example.backendtest.modules.tarefa.errors;

import com.example.backendtest.errors.BadRequest;

public class TarefaAlredyFinished extends BadRequest {
    public TarefaAlredyFinished() {
        super("Tarefa já foi finalizada");
    }
}
