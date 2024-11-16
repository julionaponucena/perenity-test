package com.example.backendtest.modules.tarefa.infra.repositories;

import com.example.backendtest.modules.tarefa.infra.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {



    @Query("SELECT t FROM Tarefa t " +
            "WHERE t.pessoa IS NULL ORDER BY t.prazo ASC LIMIT 3")
    List<Tarefa> findPendingTarefas();

}
