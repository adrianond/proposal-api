package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.builder.PropostaAutoBuilder;
import br.com.example.proposal.api.database.repository.LoginRepositoryFacade;
import br.com.example.proposal.api.database.repository.PropostaAutoRepositoryFacade;
import br.com.example.proposal.api.domain.dto.PropostaAutoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ConsultarPropostasPorUsuario {
    private final PropostaAutoRepositoryFacade facade;
    private final LoginRepositoryFacade loginRepositoryFacade;

    public List<PropostaAutoDTO> executar(Long codigoUsuaurio) {
        return facade.findAllByUsuarioAnaliseCodigo(codigoUsuaurio)
                .stream()
                .map(PropostaAutoBuilder::fromEntityResumo)
                .toList();
    }
}
