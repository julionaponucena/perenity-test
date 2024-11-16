package com.example.backendtest.modules.pessoa.infra.controllers;

import com.example.backendtest.modules.pessoa.dtos.CreatePessoaDTO;
import com.example.backendtest.modules.pessoa.dtos.UpdatePessoaDTO;
import com.example.backendtest.modules.pessoa.dtos.outs.CreatePessoaOUT;
import com.example.backendtest.modules.pessoa.infra.controllers.queryparams.MediaHoraGastaFilter;
import com.example.backendtest.modules.pessoa.infra.repositories.projections.PessoaMediaHorasGastaProj;
import com.example.backendtest.modules.pessoa.infra.repositories.projections.PessoaTotalHorasGastasProj;
import com.example.backendtest.modules.pessoa.services.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatePessoaOUT create(@Validated @RequestBody CreatePessoaDTO pessoaDTO) {
        return this.service.create(pessoaDTO);
    }

    @PutMapping("{id}")
    public void update(@PathVariable int id,
                       @Validated @RequestBody UpdatePessoaDTO pessoaDTO) {
        this.service.update(id,pessoaDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        this.service.delete(id);
    }

    @GetMapping
    public List<PessoaTotalHorasGastasProj> listPessoas() {
        return this.service.listPessoas();
    }

    @GetMapping("gastos")
    public PessoaMediaHorasGastaProj calcularMediaHorasGastas(@Validated MediaHoraGastaFilter filter) {
        return this.service.calculateAVGHorasGastas(filter);
    }
}
