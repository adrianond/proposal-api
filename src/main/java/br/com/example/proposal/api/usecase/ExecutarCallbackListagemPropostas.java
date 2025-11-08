package br.com.example.proposal.api.usecase;

import br.com.example.proposal.api.builder.PropostaAutoBuilder;
import br.com.example.proposal.api.database.entity.PropostaAuto;
import br.com.example.proposal.api.domain.dto.PropostaAutoDTO;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExecutarCallbackListagemPropostas {
    private final SimpMessagingTemplate template;
    public void executar(PropostaAuto proposta) {
        PropostaAutoDTO propostaAutoDTO = PropostaAutoBuilder.fromEntity(proposta);

        template.convertAndSend("/topic/propostas", propostaAutoDTO);
    }
}
