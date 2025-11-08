package br.com.example.proposal.api.database.repository;

import br.com.example.proposal.api.database.entity.PropostaAuto;

import java.util.List;

public interface PropostaAutoRepositoryFacade {
    PropostaAuto save(PropostaAuto propostaAuto);

    PropostaAuto findById(Long proposta);
    List<PropostaAuto> findByStatusAndUsuarioAlteracao(Long codigo, Long usuarioAlteracao);
    List<PropostaAuto> findAllByUsuario(Long usuario);

}
