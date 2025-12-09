package br.com.example.proposal.api.database.repository;

import br.com.example.proposal.api.database.entity.PropostaAuto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaAutoRepository extends JpaRepository<PropostaAuto, Long> {

    List<PropostaAuto> findAllByUsuarioAnaliseCodigo(Long usuario);

    List<PropostaAuto> findAllByUsuarioCadastroCodigo(Long usuario);

    List<PropostaAuto> findAllByUsuarioAnaliseIsNull();
}
