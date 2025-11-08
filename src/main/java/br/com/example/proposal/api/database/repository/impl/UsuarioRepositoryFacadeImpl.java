package br.com.example.proposal.api.database.repository.impl;

import br.com.example.proposal.api.database.entity.Usuario;
import br.com.example.proposal.api.database.repository.UsuarioRepository;
import br.com.example.proposal.api.database.repository.UsuarioRepositoryFacade;
import br.com.example.proposal.api.exception.UsuarioNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioRepositoryFacadeImpl implements UsuarioRepositoryFacade {
    private final UsuarioRepository repository;


    @Override
    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(String.format("Usuário não encontrado para id %d", id)));
    }
}
