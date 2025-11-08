package br.com.example.proposal.api.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class GarantiaRequest {

    @NotEmpty(message = "Marca é obrigatório")
    @JsonProperty("brand")
    private String marca;

    @NotEmpty(message = "Modelo da mãe é obrigatório")
    @JsonProperty("model")
    private String modelo;

    @NotEmpty(message = "Ano do modelo é obrigatório")
    @JsonProperty("modelYear")
    private Integer anoModelo;

    @NotEmpty(message = "Ano de fabricação é obrigatório")
    @JsonProperty("manufacturingYear")
    private Integer anoFabricacao;

    @NotEmpty(message = "Valor da garantia")
    @JsonProperty("value")
    private BigDecimal valor;
}
