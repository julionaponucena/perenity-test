package com.example.backendtest.modules.tarefa.services;

import com.example.backendtest.modules.pessoa.errors.PessoaNotFound;
import com.example.backendtest.modules.pessoa.infra.entities.Pessoa;
import com.example.backendtest.modules.pessoa.infra.repositories.PessoaRepository;
import com.example.backendtest.modules.tarefa.errors.TarefaAlredyAlocate;
import com.example.backendtest.modules.tarefa.errors.TarefaAlredyFinished;
import com.example.backendtest.modules.tarefa.errors.TarefaNotAlocate;
import com.example.backendtest.modules.tarefa.errors.TarefaNotFound;
import com.example.backendtest.modules.tarefa.infra.entities.Tarefa;
import com.example.backendtest.modules.tarefa.infra.repositories.TarefaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TarefaServiceTest {
    @InjectMocks
    private TarefaService tarefaService;

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Spy
    private Tarefa spyTarefa;

    @Test
    @DisplayName("Deve alocar uma tarefa para uma pessoa")
    void shouldAllocateAnTarefaForAPessoa() {
        String departamento="Desenvolvimento";

        Pessoa pessoa = new Pessoa();

        int id=1;

        this.spyTarefa.setDepartamento(departamento);

        Mockito.when(this.tarefaRepository.findById(id))
                .thenReturn(Optional.of(this.spyTarefa));

        Mockito.when(this.pessoaRepository.findTopByDepartamento(departamento))
                .thenReturn(Optional.of(pessoa));


        assertDoesNotThrow(() -> this.tarefaService.allocate(id));

        Mockito.verify(this.spyTarefa,Mockito.times(1))
                .setDataAlocacao(Mockito.any());

        Mockito.verify(this.spyTarefa,Mockito.times(1))
                .setPessoa(pessoa);

    }

    @Test
    @DisplayName("Não deve alocar uma tarefa caso ela não seja encontrada")
    void shouldNotAllocateAnTarefaWhenItNotFound(){
        int id=1;

        Mockito.when(this.tarefaRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(TarefaNotFound.class,() -> this.tarefaService.allocate(id));
    }

    @Test
    @DisplayName("Não deve alocar uma tarefa caso ela já tenha sido alocada")
    void shouldNotAllocateATarefaWhenItAlredyAllocate(){
        Tarefa tarefa = new Tarefa();

        int id=1;

        tarefa.setId(id);
        tarefa.setDataAlocacao(new Date());

        Mockito.when(this.tarefaRepository.findById(id))
                        .thenReturn(Optional.of(tarefa));

        assertThrows(TarefaAlredyAlocate.class,
                () -> this.tarefaService.allocate(id));
    }

    @Test
    @DisplayName("Não deve alocar uma tarefa caso uma pessoa do mesmo departamento" +
            " não seja encontrada")
    void shouldNotAllocateATarefaWhenPessoaNotFound(){
        int id=1;
        String departamento="Desenvolvimento";

        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setDepartamento(departamento);

        Mockito.when(this.tarefaRepository.findById(id))
                .thenReturn(Optional.of(tarefa));

        Mockito.when(this.pessoaRepository.findTopByDepartamento(departamento))
                .thenReturn(Optional.empty());

        assertThrows(PessoaNotFound.class,() -> this.tarefaService.allocate(id));
    }

    @Test
    @DisplayName("Deve finalizar tarefa")
    void shouldFinishTarefa(){
        int id=1;

        this.spyTarefa.setDataAlocacao(new Date());

        Mockito.when(this.tarefaRepository.findById(id))
                .thenReturn(Optional.of(this.spyTarefa));

        assertDoesNotThrow(() -> this.tarefaService.finish(id));

        Mockito.verify(this.spyTarefa,Mockito.times(1))
                .setDataFinalizacao(Mockito.any());
    }

    @Test
    @DisplayName("Não deve finalizar tarefa caso ela já tenha sido finalizada")
    void shouldNotFinishTarefaWhenItAlredyFinish(){
        int id=1;

        Tarefa tarefa = new Tarefa();

        tarefa.setId(id);
        tarefa.setDataFinalizacao(new Date());

        Mockito.when(this.tarefaRepository.findById(id))
                .thenReturn(Optional.of(tarefa));

        assertThrows(TarefaAlredyFinished.class,() -> this.tarefaService.finish(id));
    }

    @Test
    @DisplayName("Não deve finalizar tarefa caso ela ainda não tenha sido alocada")
    void shouldNotFinishTarefaWhenItNotHasBeenAlocateYet(){
        int id=1;

        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);

        Mockito.when(this.tarefaRepository.findById(id))
                .thenReturn(Optional.of(tarefa));

        assertThrows(TarefaNotAlocate.class,() -> this.tarefaService.finish(id));
    }
}