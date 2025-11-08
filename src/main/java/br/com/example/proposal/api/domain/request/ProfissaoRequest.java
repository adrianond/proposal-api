package br.com.example.proposal.api.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfissaoRequest {

    @NotEmpty(message = "Profissao é obrigatório")
    @JsonProperty("profession")
    private String profissao;

    @NotEmpty(message = "Renda é obrigatório")
    @JsonProperty("salary")
    private BigDecimal renda;
}
