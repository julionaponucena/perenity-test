package com.example.backendtest.modules.pessoa.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdatePessoaDTO(@NotBlank String nome,@NotBlank String departamento) {
}
