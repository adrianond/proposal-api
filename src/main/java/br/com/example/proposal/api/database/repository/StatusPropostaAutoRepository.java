package br.com.example.proposal.api.database.repository;

import br.com.example.proposal.api.database.entity.StatusProposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusPropostaAutoRepository extends JpaRepository<StatusProposta, Long> {

}
