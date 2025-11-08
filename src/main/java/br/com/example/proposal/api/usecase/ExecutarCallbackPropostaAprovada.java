package br.com.example.proposal.api.usecase;

import br.com.example.proposal.api.database.entity.PropostaAuto;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExecutarCallbackPropostaAprovada {
    private final SimpMessagingTemplate template;

    public void executar(PropostaAuto proposta) {
        template.convertAndSend("/topic/propostas-aprovadas", proposta);
    }
}
