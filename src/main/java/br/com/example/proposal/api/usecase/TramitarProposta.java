package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.database.entity.PropostaAuto;
import br.com.example.proposal.api.database.entity.StatusProposta;
import br.com.example.proposal.api.database.entity.Usuario;
import br.com.example.proposal.api.database.enums.PropostaStatus;
import br.com.example.proposal.api.database.repository.PropostaAutoRepositoryFacade;
import br.com.example.proposal.api.database.repository.StatusPropostaAutoRepositoryFacade;
import br.com.example.proposal.api.database.repository.UsuarioRepositoryFacade;
import br.com.example.proposal.api.domain.request.ProposalStatusUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class TramitarProposta {
    private final PropostaAutoRepositoryFacade propostaAutoRepositoryFacade;
    private final UsuarioRepositoryFacade usuarioRepositoryFacade;
    private final StatusPropostaAutoRepositoryFacade statusPropostaAutoRepositoryFacade;
    private final ExecutarCallbackPropostaAprovada callbackPropostaAprovada;

    @Transactional
    public void executar(ProposalStatusUpdateRequest request) {
        Usuario usuario = usuarioRepositoryFacade.findById(request.getUsuarioAlteracao());
        StatusProposta status = statusPropostaAutoRepositoryFacade.findById(PropostaStatus.APROVADA.getCodigo());
        PropostaAuto propostaAuto = propostaAutoRepositoryFacade.findById(request.getProposta());

        propostaAuto.setProposta(request.getProposta());
        propostaAuto.setStatus(status);
        propostaAuto.setUsuarioAlteracao(usuario);
        propostaAuto.setDataAtualizacao(LocalDateTime.now());

        propostaAutoRepositoryFacade.save(propostaAuto);

       /* List<PropostaAuto> propostasAprovadas = propostaAutoRepositoryFacade
                .findByStatusAndUsuarioAlteracao(PropostaStatus.APROVADA.getCodigo(), request.getUsuarioAlteracao());
*/
        callbackPropostaAprovada.executar(propostaAuto);
    }
}
