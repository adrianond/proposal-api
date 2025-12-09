package br.com.example.proposal.api.usecase;


import br.com.example.proposal.api.database.entity.LoginUsuario;
import br.com.example.proposal.api.database.repository.LoginRepositoryFacade;
import br.com.example.proposal.api.domain.dto.UsuarioDTO;
import br.com.example.proposal.api.domain.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EfetuarLogin {
 private final LoginRepositoryFacade loginRepositoryFacade;

    public UsuarioDTO executar(LoginRequest request) {
        LoginUsuario loginUsuario = loginRepositoryFacade.findByUsuarioAndSenha(request.getUsuario(), request.getSenha());

        return UsuarioDTO.builder()
                .id(loginUsuario.getUsuario().getCodigo())
                .nome(loginUsuario.getUsuario().getNome())
                .senha(loginUsuario.getSenha())
                .build();
    }
}
