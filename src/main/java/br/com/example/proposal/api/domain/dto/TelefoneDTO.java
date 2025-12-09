package br.com.example.proposal.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class TelefoneDTO {

    @JsonProperty("id")
    private Long id;

    @NotEmpty(message = "DDD é obrigatório")
    @JsonProperty("ddd")
    private String ddd;

    @NotEmpty(message = "Número do telefone é obrigatório")
    @JsonProperty("number")
    private String numero;

    @NotEmpty(message = "Tipo do telefone é obrigatório")
    @JsonProperty("type")
    private String tipo;
}
