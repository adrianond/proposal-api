package br.com.example.proposal.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class EnderecoDTO {

    @JsonProperty("id")
    private Long id;

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
    @JsonProperty("state")
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
