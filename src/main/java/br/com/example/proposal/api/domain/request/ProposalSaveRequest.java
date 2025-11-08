package br.com.example.proposal.api.domain.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProposalSaveRequest {

    @NotEmpty(message = "Produto é obrigatório")
    @JsonProperty("product")
    private String produto;

    @JsonProperty("user")
    private Long usuarioCadastro;

    @NotEmpty(message = "Valor é obrigatório")
    @JsonProperty("value")
    private BigDecimal valor;

    @NotNull
    @JsonProperty("customer")
    ClienteRequest clienteRequest;

    @NotNull
    @JsonProperty("garantee")
    private GarantiaRequest garantiaRequest;
}
