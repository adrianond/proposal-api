package br.com.example.proposal.api.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteRequest {

    @NotEmpty(message = "Nome é obrigatório")
    @JsonProperty("name")
    private String nome;

    @NotEmpty(message = "Nome da mãe é obrigatório")
    @JsonProperty("matherName")
    private String nomeMae;
    @JsonProperty("fatherName")
    private String nomePai;

    @NotEmpty(message = "Rg é obrigatório")
    @JsonProperty("rg")
    private String rg;

    @NotEmpty(message = "CPF é obrigatório")
    @JsonProperty("cpf")
    private String cpf;

    @NotEmpty(message = "Email é obrigatório")
    @JsonProperty("email")
    private String email;

    @NotEmpty(message = "Data de nascimento é obrigatório")
    @JsonProperty("birthdayDate")
    private LocalDate dataNascimento;

    @NotNull
    @JsonProperty("phone")
    TelefoneRequest telefoneRequest;

    @NotNull
    @JsonProperty("address")
    private EnderecoRequest enderecoRequest;

    @NotNull
    @JsonProperty("profession")
    private ProfissaoRequest profissaoRequest;
}
