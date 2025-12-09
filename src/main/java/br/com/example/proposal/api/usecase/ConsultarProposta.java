package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.builder.PropostaAutoBuilder;
import br.com.example.proposal.api.database.entity.PropostaAuto;
import br.com.example.proposal.api.database.repository.PropostaAutoRepositoryFacade;
import br.com.example.proposal.api.domain.dto.PropostaAutoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsultarProposta {
    private final PropostaAutoRepositoryFacade facade;

    public PropostaAutoDTO executar(Long id) {
        PropostaAuto propostaAuto = facade.findById(id);

        return PropostaAutoBuilder.fromEntity(propostaAuto);

    }
}
