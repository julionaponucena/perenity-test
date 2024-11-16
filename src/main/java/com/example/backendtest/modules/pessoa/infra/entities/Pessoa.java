package com.example.backendtest.modules.pessoa.infra.entities;


import com.example.backendtest.modules.tarefa.infra.entities.Tarefa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Pessoa implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String departamento;

    @OneToMany(mappedBy = "pessoa")
    private Set<Tarefa> tarefas;
}
