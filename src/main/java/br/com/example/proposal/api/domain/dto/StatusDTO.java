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
public class StatusDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("description")
    private String descricao;
}
