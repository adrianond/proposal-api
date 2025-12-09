package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.database.entity.LoginUsuario;
import br.com.example.proposal.api.database.entity.PropostaAuto;
import br.com.example.proposal.api.database.entity.StatusProposta;
import br.com.example.proposal.api.database.enums.PropostaStatus;
import br.com.example.proposal.api.database.repository.LoginRepositoryFacade;
import br.com.example.proposal.api.database.repository.PropostaAutoRepositoryFacade;
import br.com.example.proposal.api.database.repository.StatusPropostaAutoRepositoryFacade;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class AnalisarProposta {
    private final PropostaAutoRepositoryFacade propostaAutoRepositoryFacade;
    private final StatusPropostaAutoRepositoryFacade statusPropostaAutoRepositoryFacade;
    private final LoginRepositoryFacade loginRepositoryFacade;


    @Transactional
    public void executar(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUsuario loginUsuario = loginRepositoryFacade.findByUsuarioAndSenha(authentication.getName(), (String) authentication.getCredentials());
        StatusProposta status = statusPropostaAutoRepositoryFacade.findById(PropostaStatus.EM_ANALISE.getCodigo());

        PropostaAuto propostaAuto = propostaAutoRepositoryFacade.findById(id);
        propostaAuto.setUsuarioAnalise(loginUsuario.getUsuario());
        propostaAuto.setDataAtualizacao(LocalDateTime.now());
        propostaAuto.setInicioAnalise(LocalDateTime.now());
        propostaAuto.setStatus(status);

        propostaAutoRepositoryFacade.save(propostaAuto);
    }
}
