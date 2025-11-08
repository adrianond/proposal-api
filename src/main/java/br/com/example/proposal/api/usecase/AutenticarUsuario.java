package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.database.entity.LoginUsuario;
import br.com.example.proposal.api.database.repository.LoginRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AutenticarUsuario {
    private final LoginRepositoryFacade facade;

    public LoginUsuario executar(String login, String senha) {
        return facade.findByUsuarioAndSenha(login, senha);
    }
}
