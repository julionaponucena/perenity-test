package com.example.backendtest.modules.tarefa.infra.entities;

import com.example.backendtest.modules.pessoa.infra.entities.Pessoa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Tarefa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarefa")
    private int id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Date prazo;

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private int duracao;

    @Column(name = "data_alocacao")
    private Date dataAlocacao;

    @Column(name = "data_finalizacao")
    private Date dataFinalizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
}
