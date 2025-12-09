package br.com.example.proposal.api.database.repository;

import br.com.example.proposal.api.database.entity.PropostaAuto;

import java.util.List;

public interface PropostaAutoRepositoryFacade {
    PropostaAuto save(PropostaAuto propostaAuto);

    PropostaAuto findById(Long proposta);
    List<PropostaAuto> findAllByUsuarioAnaliseCodigo(Long usuario);
    List<PropostaAuto> findAllByUsuario(Long usuario);

    List<PropostaAuto> findAll();

}
