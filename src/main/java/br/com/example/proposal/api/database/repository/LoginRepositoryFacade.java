package br.com.example.proposal.api.database.repository;

import br.com.example.proposal.api.database.entity.LoginUsuario;

public interface LoginRepositoryFacade {
    LoginUsuario findByUsuarioAndSenha(String usuario, String senha);
}
