package br.com.example.proposal.api.database.repository;

import br.com.example.proposal.api.database.entity.LoginUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginUsuario, Long> {

    Optional<LoginUsuario> findByUsuarioNomeAndSenha(String usuario, String senha);
}
