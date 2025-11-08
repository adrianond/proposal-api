package br.com.example.proposal.api.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TELEFONE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TELEFONE_CLIENTE")
    @SequenceGenerator(name = "SEQ_TELEFONE_CLIENTE", sequenceName = "SEQ_TELEFONE_CLIENTE", allocationSize = 1)
    private Long id;

    @Column(name = "DDD", nullable = false, length = 20)
    private String ddd;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(length = 20)
    private String tipo;
}
