package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.database.entity.LoginUsuario;
import br.com.example.proposal.api.database.entity.PropostaAuto;
import br.com.example.proposal.api.database.repository.LoginRepositoryFacade;
import br.com.example.proposal.api.database.repository.PropostaAutoRepositoryFacade;
import br.com.example.proposal.api.domain.dto.PropostaAutoDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static br.com.example.proposal.api.builder.PropostaAutoBuilder.toEntity;

@Component
@AllArgsConstructor
public class AtualizarProposta {
    private final LoginRepositoryFacade loginRepositoryFacade;
    private final PropostaAutoRepositoryFacade propostaAutoRepositoryFacade;
    private final ExecutarCallbackListagemPropostas executarCallbackListagemPropostas;


    @Transactional
    public void executar(Long propostaId, PropostaAutoDTO propostaAutoDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LoginUsuario loginUsuario = loginRepositoryFacade.findByUsuarioAndSenha(authentication.getName(), (String) authentication.getCredentials());

        PropostaAuto propostaAuto = propostaAutoRepositoryFacade.findById(propostaId);

        toEntity(propostaAutoDTO, loginUsuario.getUsuario(), propostaAuto.getStatus(), propostaAuto);

        PropostaAuto proposta = propostaAutoRepositoryFacade.save(propostaAuto);

        executarCallbackListagemPropostas.executar(proposta);
    }
}
