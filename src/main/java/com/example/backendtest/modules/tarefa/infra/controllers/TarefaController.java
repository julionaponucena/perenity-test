package com.example.backendtest.modules.tarefa.infra.controllers;

import com.example.backendtest.modules.tarefa.dtos.CreateTarefaDTO;
import com.example.backendtest.modules.tarefa.dtos.ListPendingTarefasDTO;
import com.example.backendtest.modules.tarefa.dtos.outs.CreateTarefaOUT;
import com.example.backendtest.modules.tarefa.services.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTarefaOUT create(@Validated @RequestBody CreateTarefaDTO tarefaDTO) {
        return this.service.create(tarefaDTO);
    }

    @PutMapping("alocar/{id}")
    public void alocateTarefa(@PathVariable int id) {
        this.service.allocate(id);
    }

    @GetMapping("pendentes")
    public List<ListPendingTarefasDTO> listPendingTarefasDTOS(){
        return this.service.listPendingTarefas();
    }

    @PutMapping("finalizar/{id}")
    public void finish(@PathVariable int id) {
        this.service.finish(id);
    }
}
