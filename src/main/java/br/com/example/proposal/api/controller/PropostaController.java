package br.com.example.proposal.api.controller;

import br.com.example.proposal.api.domain.dto.PropostaAutoDTO;
import br.com.example.proposal.api.domain.request.ProposalSaveRequest;
import br.com.example.proposal.api.domain.request.ProposalStatusUpdateRequest;
import br.com.example.proposal.api.usecase.CadastrarProposta;
import br.com.example.proposal.api.usecase.ConsultarPropostas;
import br.com.example.proposal.api.usecase.TramitarProposta;
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
    private final ConsultarPropostas consultarPropostas;

    @PostMapping()
    public ResponseEntity<Void> cadastrarProposta(@RequestBody @Valid ProposalSaveRequest request) {
        cadastrarProposta.executar(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/aprove")
    public ResponseEntity<Void> aprovarProposta(@RequestBody ProposalStatusUpdateRequest request) {
        tramitarProposta.executar(request);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<PropostaAutoDTO> consultarPropostas() {
        return consultarPropostas.executar();
    }
}
