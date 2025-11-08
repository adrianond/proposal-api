package br.com.example.proposal.api.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ENDERECO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO_CLIENTE")
    @SequenceGenerator(name = "SEQ_ENDERECO_CLIENTE", sequenceName = "SEQ_ENDERECO_CLIENTE", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 150)
    private String logradouro;

    @Column(length = 100)
    private String bairro;

    @Column(length = 8)
    private String cep;

    @Column(length = 2)
    private String estado;

    @Column(length = 100)
    private String cidade;

    @Column(length = 10)
    private Integer numero;

    @Column(length = 100)
    private String complemento;

}
