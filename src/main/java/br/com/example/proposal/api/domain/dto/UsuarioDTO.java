package br.com.example.proposal.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UsuarioDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("password")
    private String senha;
}
