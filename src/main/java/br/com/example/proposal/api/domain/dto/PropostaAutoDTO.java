package br.com.example.proposal.api.domain.dto;

import br.com.example.proposal.api.database.enums.PropostaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PropostaAutoDTO {
    private Long proposta;
    private PropostaStatus status;
    private String produto;

    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime inicioAnalise;
    private LocalDateTime fimAnalise;
}
