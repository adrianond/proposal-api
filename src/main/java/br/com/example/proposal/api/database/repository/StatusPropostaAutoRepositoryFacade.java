package br.com.example.proposal.api.database.repository;

import br.com.example.proposal.api.database.entity.StatusProposta;

public interface StatusPropostaAutoRepositoryFacade {
    StatusProposta findById(Long id);
}
