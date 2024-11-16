package com.example.backendtest.modules.pessoa.services;

import com.example.backendtest.modules.pessoa.dtos.CreatePessoaDTO;
import com.example.backendtest.modules.pessoa.dtos.UpdatePessoaDTO;
import com.example.backendtest.modules.pessoa.dtos.outs.CreatePessoaOUT;
import com.example.backendtest.modules.pessoa.errors.PessoaNotFound;
import com.example.backendtest.modules.pessoa.infra.controllers.queryparams.MediaHoraGastaFilter;
import com.example.backendtest.modules.pessoa.infra.entities.Pessoa;
import com.example.backendtest.modules.pessoa.infra.repositories.PessoaRepository;
import com.example.backendtest.modules.pessoa.infra.repositories.projections.PessoaMediaHorasGastaProj;
import com.example.backendtest.modules.pessoa.infra.repositories.projections.PessoaTotalHorasGastasProj;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Throwable.class)
    public CreatePessoaOUT create(CreatePessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();

        pessoa.setDepartamento(pessoaDTO.departamento());
        pessoa.setNome(pessoaDTO.nome());

        this.repository.save(pessoa);

        return new CreatePessoaOUT(pessoa);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void update(int id,UpdatePessoaDTO pessoaDTO) {
        Pessoa pessoa = this.repository.findById(id)
                .orElseThrow(PessoaNotFound::new);

        pessoa.setDepartamento(pessoaDTO.departamento());
        pessoa.setNome(pessoaDTO.nome());

        this.repository.save(pessoa);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void delete(int id) {
        boolean exists =this.repository.existsById(id);

        if(!exists){
            throw new PessoaNotFound();
        }

        this.repository.deleteById(id);
    }

    public List<PessoaTotalHorasGastasProj> listPessoas(){
        return this.repository.listPessoasTotalHorasGastasProj();
    }

    public PessoaMediaHorasGastaProj calculateAVGHorasGastas
            (MediaHoraGastaFilter filter){
        return this.repository.calculatePessoaTotalHourSpent(
                filter.nome(),
                filter.dataInicio(),
                filter.dataFim()
        ).orElseThrow(PessoaNotFound::new);
    }
}
