package br.com.example.proposal.api.database.repository;

import br.com.example.proposal.api.database.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
