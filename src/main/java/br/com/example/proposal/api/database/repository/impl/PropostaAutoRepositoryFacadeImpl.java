package br.com.example.proposal.api.database.repository.impl;

import br.com.example.proposal.api.database.entity.PropostaAuto;
import br.com.example.proposal.api.database.repository.PropostaAutoRepository;
import br.com.example.proposal.api.database.repository.PropostaAutoRepositoryFacade;
import br.com.example.proposal.api.exception.PropostaNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PropostaAutoRepositoryFacadeImpl implements PropostaAutoRepositoryFacade {
    private final PropostaAutoRepository repository;

    @Override
    public PropostaAuto save(PropostaAuto propostaAuto) {
        return repository.save(propostaAuto);
    }

    @Override
    public PropostaAuto findById(Long proposta) {
        return repository.findById(proposta).orElseThrow(() ->
                new PropostaNotFoundException(String.format("Proposta %d não encontrada", proposta)));
    }

    @Override
    public List<PropostaAuto> findAllByUsuarioAnaliseCodigo(Long usuario) {
        return repository.findAllByUsuarioAnaliseCodigo(usuario);
    }

    @Override
    public List<PropostaAuto> findAllByUsuario(Long usuario) {
        return repository.findAllByUsuarioCadastroCodigo(usuario);
    }

    @Override
    public List<PropostaAuto> findAll() {
        return repository.findAllByUsuarioAnaliseIsNull();
    }
}
