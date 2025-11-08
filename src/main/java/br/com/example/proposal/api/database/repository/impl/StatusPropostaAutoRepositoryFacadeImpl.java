package br.com.example.proposal.api.database.repository.impl;

import br.com.example.proposal.api.database.entity.StatusProposta;
import br.com.example.proposal.api.database.repository.StatusPropostaAutoRepository;
import br.com.example.proposal.api.database.repository.StatusPropostaAutoRepositoryFacade;
import br.com.example.proposal.api.exception.StatusPropostaNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatusPropostaAutoRepositoryFacadeImpl implements StatusPropostaAutoRepositoryFacade {
    private final StatusPropostaAutoRepository repository;


    @Override
    public StatusProposta findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new StatusPropostaNotFoundException(String.format("Status não encontrado para id %d", id)));
    }
}
