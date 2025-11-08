package br.com.example.proposal.api.controller;

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
    public ResponseEntity<Void> efetuarLogin(@RequestBody LoginRequest request) {
        efetuarLogin.executar(request);
        return ResponseEntity.ok().build();
    }

}
