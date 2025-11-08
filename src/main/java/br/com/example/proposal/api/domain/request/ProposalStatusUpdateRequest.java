package br.com.example.proposal.api.domain.request;


import br.com.example.proposal.api.database.enums.PropostaStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProposalStatusUpdateRequest {

    @JsonProperty("proposal")
    private Long proposta;

    @JsonProperty("user")
    private Long usuarioAlteracao;

    @JsonProperty("status")
    private PropostaStatus status;
}
