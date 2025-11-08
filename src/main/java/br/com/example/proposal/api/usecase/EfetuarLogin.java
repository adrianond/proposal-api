package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.database.repository.LoginRepositoryFacade;
import br.com.example.proposal.api.domain.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EfetuarLogin {
 private final LoginRepositoryFacade loginRepositoryFacade;

    public void executar(LoginRequest request) {
        loginRepositoryFacade.findByUsuarioAndSenha(request.getUsuario(), request.getSenha());
    }
}
