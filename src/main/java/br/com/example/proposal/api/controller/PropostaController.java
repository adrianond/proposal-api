package br.com.example.proposal.api.controller;

import br.com.example.proposal.api.domain.dto.PropostaAutoDTO;
import br.com.example.proposal.api.domain.request.ProposalStatusUpdateRequest;
import br.com.example.proposal.api.usecase.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/proposals")
@RequiredArgsConstructor
public class PropostaController {
    private final TramitarProposta tramitarProposta;
    private final CadastrarProposta cadastrarProposta;
    private final ConsultarPropostasPorUsuario consultarPropostasPorUsuario;
    private final ConsultarProposta consultarProposta;
    private final AtualizarProposta atualizarProposta;
    private final ConsultarPropostas consultarPropostas;

    private final AnalisarProposta analisarProposta;

    @PostMapping()
    public ResponseEntity<Void> cadastrarProposta(@RequestBody @Valid PropostaAutoDTO propostaAutoDTO) {
        cadastrarProposta.executar(propostaAutoDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/aprove")
    public ResponseEntity<Void> aprovarProposta(@RequestBody ProposalStatusUpdateRequest request) {
        tramitarProposta.executar(request);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{codigo}")
    public ResponseEntity<List<PropostaAutoDTO>> consultarPropostasPorUsuario(@PathVariable("codigo") Long codigo) {

        return ResponseEntity.ok(consultarPropostasPorUsuario.executar(codigo));
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public ResponseEntity<List<PropostaAutoDTO>> consultarPropostas() {
        return ResponseEntity.ok(consultarPropostas.executar());
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<PropostaAutoDTO> consultar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(consultarProposta.executar(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody @Valid PropostaAutoDTO propostaAutoDTO, @PathVariable("id") Long id) {
        atualizarProposta.executar(id, propostaAutoDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/take")
    public ResponseEntity<Void> takeProposal(@PathVariable Long id) {
        analisarProposta.executar(id);
        return ResponseEntity.ok().build();
    }

}
