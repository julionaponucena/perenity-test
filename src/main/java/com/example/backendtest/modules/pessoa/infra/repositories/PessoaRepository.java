package com.example.backendtest.modules.pessoa.infra.repositories;

import com.example.backendtest.modules.pessoa.infra.entities.Pessoa;
import com.example.backendtest.modules.pessoa.infra.repositories.projections.PessoaMediaHorasGastaProj;
import com.example.backendtest.modules.pessoa.infra.repositories.projections.PessoaTotalHorasGastasProj;
import com.example.backendtest.modules.pessoa.infra.repositories.projections.DepartamentoProj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {

    boolean existsById(int id);

    Optional<Pessoa> findTopByDepartamento(String departamento);

    @Query("SELECT p.nome AS nome, p.departamento AS departamento, " +
            "CONCAT(SUM(t.duracao),' horas') AS totalHorasGastas" +
            " FROM Pessoa p JOIN p.tarefas t GROUP BY p.nome, p.departamento")
    List<PessoaTotalHorasGastasProj> listPessoasTotalHorasGastasProj();

    @Query("SELECT p.departamento AS departamento, COUNT(DISTINCT p) AS totalPessoas, " +
            "COUNT(DISTINCT t) AS totalTarefas FROM Pessoa p LEFT JOIN  " +
            "Tarefa t ON p.departamento = t.departamento " +
            "GROUP BY p.departamento")
    List<DepartamentoProj> listDepartamentoProj();

    @Query("SELECT CONCAT(FLOOR(AVG(t.duracao)), ' horas') AS mediaHorasGastas" +
            " FROM Pessoa p JOIN p.tarefas t" +
            " WHERE p.nome=:nome AND t.dataAlocacao >=:dataAlocacao" +
            " AND t.dataFinalizacao <=:dataFim")
    Optional<PessoaMediaHorasGastaProj> calculatePessoaTotalHourSpent(
            @Param("nome") String nome,
            @Param("dataAlocacao") LocalDateTime dataAlocacao,
            @Param("dataFim") LocalDateTime dataFim
    );
}