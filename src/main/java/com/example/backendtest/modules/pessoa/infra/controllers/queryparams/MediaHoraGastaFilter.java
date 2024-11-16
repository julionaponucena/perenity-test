package com.example.backendtest.modules.pessoa.infra.controllers.queryparams;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record MediaHoraGastaFilter(
        @NotBlank String nome,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime dataInicio,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime dataFim
        ) {
}
