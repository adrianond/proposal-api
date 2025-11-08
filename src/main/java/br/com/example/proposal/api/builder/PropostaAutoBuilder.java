package br.com.example.proposal.api.builder;

import br.com.example.proposal.api.database.entity.*;
import br.com.example.proposal.api.database.enums.PropostaStatus;
import br.com.example.proposal.api.domain.dto.PropostaAutoDTO;
import br.com.example.proposal.api.domain.request.*;

import java.time.LocalDateTime;

public class PropostaAutoBuilder {

    public static PropostaAuto toEntity(ProposalSaveRequest proposalSaveRequest, Usuario usuario, StatusProposta status) {
        return PropostaAuto.builder()
                .usuarioCadastro(usuario)
                .dataCadastro(LocalDateTime.now())
                .produto(proposalSaveRequest.getProduto())
                .status(status)
                .valor(proposalSaveRequest.getValor())
                .cliente(buildCliente(proposalSaveRequest.getClienteRequest()))
                .garantia(buildGarantia(proposalSaveRequest.getGarantiaRequest()))
                .build();

    }

    private static Garantia buildGarantia(GarantiaRequest garantiaRequest) {
        return Garantia.builder()
                .marca(garantiaRequest.getMarca())
                .anoFabricacao(garantiaRequest.getAnoFabricacao())
                .anoModelo(garantiaRequest.getAnoModelo())
                .valor(garantiaRequest.getValor())
                .modelo(garantiaRequest.getModelo())
                .build();
    }

    private static Cliente buildCliente(ClienteRequest clienteRequest) {
       return Cliente.builder()
               .cpf(clienteRequest.getCpf())
               .nome(clienteRequest.getNome())
               .rg(clienteRequest.getRg())
               .dataNascimento(clienteRequest.getDataNascimento())
               .email(clienteRequest.getEmail())
               .nomePai(clienteRequest.getNomePai())
               .nomeMae(clienteRequest.getNomeMae())
               .endereco(buildEndereco(clienteRequest.getEnderecoRequest()))
               .telefone(buildTelefone(clienteRequest.getTelefoneRequest()))
               .profissao(buildProfissao(clienteRequest.getProfissaoRequest()))
               .build();
    }

    private static Profissao buildProfissao(ProfissaoRequest profissaoRequest) {
        return Profissao.builder()
                .profissao(profissaoRequest.getProfissao())
                .renda(profissaoRequest.getRenda())
                .build();
    }

    private static Telefone buildTelefone(TelefoneRequest telefoneRequest) {
        return Telefone.builder()
                .ddd(telefoneRequest.getDdd())
                .numero(telefoneRequest.getNumero())
                .tipo(telefoneRequest.getTpo())
                .build();
    }

    private static Endereco buildEndereco(EnderecoRequest enderecoRequest) {
        return Endereco.builder()
                .cep(enderecoRequest.getCep())
                .bairro(enderecoRequest.getBairro())
                .cidade(enderecoRequest.getCidade())
                .complemento(enderecoRequest.getComplemento())
                .estado(enderecoRequest.getEstado())
                .logradouro(enderecoRequest.getLogradouro())
                .numero(enderecoRequest.getNumero())
                .build();
    }

    public static PropostaAutoDTO fromEntity(PropostaAuto propostaAuto) {
        return PropostaAutoDTO.builder()
                .proposta(propostaAuto.getProposta())
                .dataAtualizacao(propostaAuto.getDataAtualizacao())
                .fimAnalise(propostaAuto.getFimAnalise())
                .inicioAnalise(propostaAuto.getInicioAnalise())
                .dataCadastro(propostaAuto.getDataCadastro())
                .produto(propostaAuto.getProduto())
                .status(PropostaStatus.fromCodigo(propostaAuto.getStatus().getCodigo()))
                .build();

    }
}
