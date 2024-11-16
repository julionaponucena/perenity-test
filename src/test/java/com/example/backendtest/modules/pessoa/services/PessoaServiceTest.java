package com.example.backendtest.modules.pessoa.services;

import com.example.backendtest.modules.pessoa.dtos.UpdatePessoaDTO;
import com.example.backendtest.modules.pessoa.errors.PessoaNotFound;
import com.example.backendtest.modules.pessoa.infra.entities.Pessoa;
import com.example.backendtest.modules.pessoa.infra.repositories.PessoaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PessoaServiceTest {
    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    @DisplayName("Deve atualizar Pessoa")
    void shouldeUpdatePessoa() {
        Pessoa findedPessoa = new Pessoa();

        findedPessoa.setId(1);

        Mockito.when(this.pessoaRepository.findById(1)).thenReturn(
                Optional.of(findedPessoa)
        );


        UpdatePessoaDTO pessoaDTO = this.createUpdatePessoaDTO();

        assertDoesNotThrow(() -> this.pessoaService.update(1,pessoaDTO));
    }

    @Test
    @DisplayName("Não deve atualizar Pessoa caso ela não seja encontrada")
    void shouldNotUpdatePessoaWhenItNotFound(){
        Mockito.when(this.pessoaRepository.findById(1))
                .thenReturn(Optional.empty());

        UpdatePessoaDTO pessoaDTO = this.createUpdatePessoaDTO();

        assertThrows(PessoaNotFound.class,
                () -> this.pessoaService.update(1,pessoaDTO));
    }

    @Test
    @DisplayName("Deve deletar Pessoa")
    void shouldeDeletePessoa() {

        Mockito.when(this.pessoaRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> this.pessoaService.delete(1));
    }

    @Test
    @DisplayName("Não deve deletar Pessoa caso ela não seja encontrada")
    void shouldNotDeletePessoaWhenItNotFound(){
        Mockito.when(this.pessoaRepository.existsById(1))
                .thenReturn(false);

        assertThrows(PessoaNotFound.class,() -> this.pessoaService.delete(1));
    }

    private UpdatePessoaDTO createUpdatePessoaDTO() {
        return new UpdatePessoaDTO(
                "José", "Implantação"
        );
    }
}