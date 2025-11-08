package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.database.entity.PropostaAuto;
import br.com.example.proposal.api.database.entity.StatusProposta;
import br.com.example.proposal.api.database.entity.Usuario;
import br.com.example.proposal.api.database.enums.PropostaStatus;
import br.com.example.proposal.api.database.repository.PropostaAutoRepositoryFacade;
import br.com.example.proposal.api.database.repository.StatusPropostaAutoRepositoryFacade;
import br.com.example.proposal.api.database.repository.UsuarioRepositoryFacade;
import br.com.example.proposal.api.domain.request.ProposalSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static br.com.example.proposal.api.builder.PropostaAutoBuilder.toEntity;

@Component
@AllArgsConstructor
public class CadastrarProposta {
    private final PropostaAutoRepositoryFacade propostaAutoRepositoryFacade;
    private final UsuarioRepositoryFacade usuarioRepositoryFacade;
    private final StatusPropostaAutoRepositoryFacade statusPropostaAutoRepositoryFacade;
    private final ExecutarCallbackListagemPropostas executarCallbackListagemPropostas;


    @Transactional
    public void executar(ProposalSaveRequest request) {
        Usuario usuario = usuarioRepositoryFacade.findById(request.getUsuarioCadastro());
        StatusProposta status = statusPropostaAutoRepositoryFacade.findById(PropostaStatus.AGUARDANDO_ANALISE.getCodigo());

        PropostaAuto proposta = propostaAutoRepositoryFacade.save(toEntity(request, usuario, status));
        executarCallbackListagemPropostas.executar(proposta);
    }
}
