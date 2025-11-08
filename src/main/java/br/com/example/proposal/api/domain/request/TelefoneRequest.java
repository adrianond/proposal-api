package br.com.example.proposal.api.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TelefoneRequest {

    @NotEmpty(message = "DDD é obrigatório")
    @JsonProperty("ddd")
    private String ddd;

    @NotEmpty(message = "Número do telefone é obrigatório")
    @JsonProperty("number")
    private String numero;

    @NotEmpty(message = "Tipo do telefone é obrigatório")
    @JsonProperty("type")
    private String tpo;
}
