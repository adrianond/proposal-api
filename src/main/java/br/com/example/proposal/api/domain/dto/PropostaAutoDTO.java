package br.com.example.proposal.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PropostaAutoDTO {

    @JsonProperty("id")
    private Long id;

    @NotEmpty(message = "Produto é obrigatório")
    @JsonProperty("product")
    private String produto;

    @JsonProperty("user")
    private UsuarioDTO usuarioDTO;

    @NotEmpty(message = "Valor é obrigatório")
    @JsonProperty("value")
    private BigDecimal valor;

    @JsonProperty("createdAt")
    private LocalDateTime dataCadastro;

    @JsonProperty("updateAt")
    private LocalDateTime dataAtualizacao;

    @JsonProperty("startAnalysisDate")
    private LocalDateTime inicioAnalise;

    @JsonProperty("finalAnalysisDate")
    private LocalDateTime fimAnalise;

    @NotNull
    @JsonProperty("customer")
    ClienteDTO clienteDTO;

    @NotNull
    @JsonProperty("garantee")
    private GarantiaDTO garantiaDTO;

    @JsonProperty("status")
    private StatusDTO status;



}
