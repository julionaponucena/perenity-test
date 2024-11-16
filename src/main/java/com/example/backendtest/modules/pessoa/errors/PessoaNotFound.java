package com.example.backendtest.modules.pessoa.errors;

import com.example.backendtest.errors.EntityNotFound;

public class PessoaNotFound extends EntityNotFound {
    public PessoaNotFound() {
        super("Pessoa não encontrada");
    }
}
