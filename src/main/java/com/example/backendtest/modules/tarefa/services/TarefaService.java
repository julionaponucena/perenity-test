package com.example.backendtest.modules.tarefa.services;

import com.example.backendtest.modules.pessoa.errors.PessoaNotFound;
import com.example.backendtest.modules.pessoa.infra.entities.Pessoa;
import com.example.backendtest.modules.pessoa.infra.repositories.PessoaRepository;
import com.example.backendtest.modules.tarefa.dtos.CreateTarefaDTO;
import com.example.backendtest.modules.tarefa.dtos.ListPendingTarefasDTO;
import com.example.backendtest.modules.tarefa.dtos.outs.CreateTarefaOUT;
import com.example.backendtest.modules.tarefa.errors.TarefaAlredyAlocate;
import com.example.backendtest.modules.tarefa.errors.TarefaAlredyFinished;
import com.example.backendtest.modules.tarefa.errors.TarefaNotAlocate;
import com.example.backendtest.modules.tarefa.errors.TarefaNotFound;
import com.example.backendtest.modules.tarefa.infra.entities.Tarefa;
import com.example.backendtest.modules.tarefa.infra.repositories.TarefaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final PessoaRepository pessoaRepository;

    public TarefaService(TarefaRepository tarefaRepository,
                         PessoaRepository pessoaRepository) {
        this.tarefaRepository = tarefaRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public CreateTarefaOUT create(CreateTarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa();

        tarefa.setDepartamento(tarefaDTO.departamento());
        tarefa.setDuracao(tarefaDTO.duracao());
        tarefa.setTitulo(tarefaDTO.titulo());
        tarefa.setDescricao(tarefaDTO.descricao());
        tarefa.setPrazo(tarefaDTO.prazo());

        this.tarefaRepository.save(tarefa);

        return new CreateTarefaOUT(tarefa);
    }


    @Transactional(rollbackFor = Throwable.class)
    public void allocate(int id){

        Tarefa tarefa = this.tarefaRepository.findById(id)
                .orElseThrow(TarefaNotFound::new);

        if(tarefa.getDataAlocacao() !=null){
            throw new TarefaAlredyAlocate();
        }

        Pessoa pessoa = this.pessoaRepository
                .findTopByDepartamento(tarefa.getDepartamento())
                .orElseThrow(PessoaNotFound::new);

        tarefa.setPessoa(pessoa);
        tarefa.setDataAlocacao(new Date());

        this.tarefaRepository.save(tarefa);

    }

    @Transactional(rollbackFor = Throwable.class)
    public void finish(int id){

        Tarefa tarefa = this.tarefaRepository.findById(id)
                        .orElseThrow(TarefaNotFound::new);


        if(tarefa.getDataFinalizacao() !=null){
            throw new TarefaAlredyFinished();
        }

        if(tarefa.getDataAlocacao() == null){
            throw new TarefaNotAlocate();
        }

        tarefa.setDataFinalizacao(new Date());

        this.tarefaRepository.save(tarefa);
    }

    public List<ListPendingTarefasDTO> listPendingTarefas(){
        List<Tarefa> tarefas = this.tarefaRepository.findPendingTarefas();

        return tarefas.stream().map(ListPendingTarefasDTO::new).toList();
    }
}
