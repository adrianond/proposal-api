package br.com.example.proposal.api.database.repository;

import br.com.example.proposal.api.database.entity.Usuario;

public interface UsuarioRepositoryFacade {
    Usuario findById(Long id);
}
