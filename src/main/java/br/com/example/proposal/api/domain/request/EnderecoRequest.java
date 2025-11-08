package br.com.example.proposal.api.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnderecoRequest {

    @NotEmpty(message = "Endereço é obrigatório")
    @JsonProperty("address")
    private String logradouro;

    @NotEmpty(message = "Bairro é obrigatório")
    @JsonProperty("neighborhood")
    private String bairro;

    @NotEmpty(message = "CEP é obrigatório")
    @JsonProperty("cep")
    private String cep;

    @NotEmpty(message = "Estado é obrigatório")
    @JsonProperty("estado")
    private String estado;

    @NotEmpty(message = "Cidade é obrigatório")
    @JsonProperty("city")
    private String cidade;

    @NotEmpty(message = "Número do endereço é obrigatório")
    @JsonProperty("number")
    private Integer numero;

    @NotEmpty(message = "Complemento do enredeço é obrigatório")
    @JsonProperty("complement")
    private String complemento;

}
