package br.com.example.proposal.api.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "PROFISSAO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROFISSAO")
    @SequenceGenerator(name = "SEQ_PROFISSAO", sequenceName = "SEQ_PROFISSAO", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 20)
    private String profissao;

    @Column(nullable = false, length = 20)
    private BigDecimal renda;
}
