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
public class GarantiaDTO {

    @JsonProperty("id")
    private Long id;

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
