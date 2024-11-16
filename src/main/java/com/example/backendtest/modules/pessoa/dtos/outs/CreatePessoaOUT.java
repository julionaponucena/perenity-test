package com.example.backendtest.modules.pessoa.dtos.outs;

import com.example.backendtest.modules.pessoa.infra.entities.Pessoa;

public record CreatePessoaOUT(int id,String nome, String departamento) {

    public CreatePessoaOUT(Pessoa pessoa) {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getDepartamento());
    }
}
