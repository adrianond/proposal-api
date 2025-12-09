package br.com.example.proposal.api.controller;

import br.com.example.proposal.api.domain.dto.UsuarioDTO;
import br.com.example.proposal.api.domain.request.LoginRequest;
import br.com.example.proposal.api.usecase.EfetuarLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/login")
@RequiredArgsConstructor
public class LoginController {
    private final EfetuarLogin efetuarLogin;


    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioDTO> efetuarLogin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok( efetuarLogin.executar(request));
    }

}
