package com.example.backendtest.modules.tarefa.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateTarefaDTO(@NotBlank String titulo, @NotBlank String descricao,
                              @NotNull Date prazo,
                              @NotBlank String departamento,
                              @NotNull int duracao) {
}
