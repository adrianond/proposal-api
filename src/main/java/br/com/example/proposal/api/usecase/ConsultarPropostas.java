package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.builder.PropostaAutoBuilder;
import br.com.example.proposal.api.database.entity.LoginUsuario;
import br.com.example.proposal.api.database.repository.LoginRepositoryFacade;
import br.com.example.proposal.api.database.repository.PropostaAutoRepositoryFacade;
import br.com.example.proposal.api.domain.dto.PropostaAutoDTO;
import br.com.example.proposal.api.exception.UsuarioNaoAutenticadoException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ConsultarPropostas {
    private final PropostaAutoRepositoryFacade facade;
    private final LoginRepositoryFacade loginRepositoryFacade;

    public List<PropostaAutoDTO> executar() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            throw new UsuarioNaoAutenticadoException("Usuário não autenticado");

        LoginUsuario loginUsuario = loginRepositoryFacade.findByUsuarioAndSenha(authentication.getName(), (String) authentication.getCredentials());

        return facade.findAllByUsuario(loginUsuario.getUsuario().getCodigo())
                .stream()
                .map(PropostaAutoBuilder::fromEntity)
                .toList();
    }
}
