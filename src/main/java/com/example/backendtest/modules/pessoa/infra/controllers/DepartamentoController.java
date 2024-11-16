package com.example.backendtest.modules.pessoa.infra.controllers;

import com.example.backendtest.modules.pessoa.infra.repositories.projections.DepartamentoProj;
import com.example.backendtest.modules.pessoa.services.DepartamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("departamentos")
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List<DepartamentoProj> list(){
        return this.departamentoService.list();
    }
}
