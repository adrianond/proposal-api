package br.com.example.proposal.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ProfissaoDTO {

    @JsonProperty("id")
    private Long id;

    @NotEmpty(message = "Profissao é obrigatório")
    @JsonProperty("profession")
    private String profissao;

    @NotEmpty(message = "Renda é obrigatório")
    @JsonProperty("salary")
    private BigDecimal renda;
}
