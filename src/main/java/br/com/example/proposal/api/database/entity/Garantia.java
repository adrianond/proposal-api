package br.com.example.proposal.api.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "GARANTIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Garantia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GARANTIA")
    @SequenceGenerator(name = "SEQ_GARANTIA", sequenceName = "SEQ_GARANTIA", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 150)
    private String marca;

    @Column(length = 100)
    private String modelo;

    @Column(length = 8)
    private Integer anoModelo;

    @Column(length = 8)
    private Integer anoFabricacao;

    @Column()
    private BigDecimal valor;
}
