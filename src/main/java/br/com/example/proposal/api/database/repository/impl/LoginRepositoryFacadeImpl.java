package br.com.example.proposal.api.database.repository.impl;

import br.com.example.proposal.api.database.entity.LoginUsuario;
import br.com.example.proposal.api.database.repository.LoginRepository;
import br.com.example.proposal.api.database.repository.LoginRepositoryFacade;
import br.com.example.proposal.api.exception.UsuarioNaoAutenticadoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginRepositoryFacadeImpl implements LoginRepositoryFacade {
    private final LoginRepository repository;


    @Override
    public LoginUsuario findByUsuarioAndSenha(String usuario, String senha) {
        return repository.findByUsuarioNomeAndSenha(usuario, senha)
                .orElseThrow(() -> new UsuarioNaoAutenticadoException(String.format("Usuário não autenticado")));
    }
}
