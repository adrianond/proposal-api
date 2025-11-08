package br.com.example.proposal.api.domain.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    @JsonProperty("username")
    private String usuario;

    @JsonProperty("password")
    private String senha;
}
