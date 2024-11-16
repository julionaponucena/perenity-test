package com.example.backendtest.modules.pessoa.services;

import com.example.backendtest.modules.pessoa.infra.repositories.PessoaRepository;
import com.example.backendtest.modules.pessoa.infra.repositories.projections.DepartamentoProj;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {
    private final PessoaRepository pessoaRepository;

    public DepartamentoService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<DepartamentoProj> list() {
        return this.pessoaRepository.listDepartamentoProj();
    }
}
