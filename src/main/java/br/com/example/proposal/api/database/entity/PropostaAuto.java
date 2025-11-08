package br.com.example.proposal.api.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "PROPOSTA_AUTO")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropostaAuto {
    @Id
    @SequenceGenerator(name = "SEQ_PROPOSTA_AUTO", sequenceName = "SEQ_PROPOSTA_AUTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROPOSTA_AUTO")
    @Column(name = "proposta")
    private Long proposta;

    @ManyToOne(targetEntity = StatusProposta.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "status", referencedColumnName = "codigo", nullable = false)
    private StatusProposta status;

    @Column(name = "produto")
    private String produto;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "inicio_analise")
    private LocalDateTime inicioAnalise;

    @Column(name = "fim_analise")
    private LocalDateTime fimAnalise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_cadastro", referencedColumnName = "codigo", nullable = false)
    private Usuario usuarioCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_alteracao", referencedColumnName = "codigo")
    private Usuario usuarioAlteracao;

    @Column(name = "valor")
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GARANTIA_ID", referencedColumnName = "ID")
    private Garantia garantia;
}
