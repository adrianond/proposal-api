package br.com.example.proposal.api.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "CLIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CLIENTE_")
    @SequenceGenerator(name = "SEQUENCE_CLIENTE_", sequenceName = "SEQUENCE_CLIENTE_", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "NOME_MAE", nullable = false, length = 100)
    private String nomeMae;

    @Column(name = "NOME_PAI", length = 100)
    private String nomePai;

    @Column(nullable = false, unique = true, length = 11)
    private String rg;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "TELEFONE_ID", referencedColumnName = "ID")
    private Telefone telefone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ENDERECO_ID", referencedColumnName = "ID")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PROFISSAO_ID", referencedColumnName = "ID")
    private Profissao profissao;
}
