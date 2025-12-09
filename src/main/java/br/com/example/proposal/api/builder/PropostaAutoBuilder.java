package br.com.example.proposal.api.builder;

import br.com.example.proposal.api.database.entity.*;
import br.com.example.proposal.api.domain.dto.*;
import br.com.example.proposal.api.utils.MoedaFormatter;

import java.time.LocalDateTime;

import static br.com.example.proposal.api.utils.MoedaFormatter.stringParaBigDecimal;

public class PropostaAutoBuilder {

    public static PropostaAuto toEntity(PropostaAutoDTO propostaAutoDTO, Usuario usuario, StatusProposta status, PropostaAuto propostaAuto) {
        if (propostaAuto == null) {
            propostaAuto = new PropostaAuto();
            propostaAuto.setUsuarioCadastro(usuario);
            propostaAuto.setDataCadastro(LocalDateTime.now());
        } else {
            propostaAuto.setUsuarioAlteracao(usuario);
            propostaAuto.setDataAtualizacao(LocalDateTime.now());
        }
        propostaAuto.setProduto(propostaAutoDTO.getProduto());
        propostaAuto.setStatus(status);
        propostaAuto.setValor(propostaAutoDTO.getValor());
        propostaAuto.setCliente(buildCliente(propostaAutoDTO.getClienteDTO(), propostaAuto.getCliente()));
        propostaAuto.setGarantia(buildGarantia(propostaAutoDTO.getGarantiaDTO(), propostaAuto.getGarantia()));
        return propostaAuto;
    }

    private static Garantia buildGarantia(GarantiaDTO garantiaDTO, Garantia garantia) {
        if (garantia == null) {
            garantia = new Garantia();
        }
        garantia.setMarca(garantiaDTO.getMarca());
        garantia.setAnoFabricacao(garantiaDTO.getAnoFabricacao());
        garantia.setAnoModelo(garantiaDTO.getAnoModelo());
        garantia.setValor(garantiaDTO.getValor());
        garantia.setModelo(garantiaDTO.getModelo());
        return garantia;
    }

    private static Cliente buildCliente(ClienteDTO clienteDTO, Cliente cliente) {
        if (cliente == null) {
            cliente = new Cliente();
        }
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setRg(clienteDTO.getRg());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setNomePai(clienteDTO.getNomePai());
        cliente.setNomeMae(clienteDTO.getNomeMae());
        cliente.setEndereco(buildEndereco(clienteDTO.getEnderecoDTO(), cliente.getEndereco()));
        cliente.setTelefone(buildTelefone(clienteDTO.getTelefoneDTO(), cliente.getTelefone()));
        cliente.setProfissao(buildProfissao(clienteDTO.getProfissaoDTO(), cliente.getProfissao()));
        return cliente;
    }

    private static Profissao buildProfissao(ProfissaoDTO profissaoDTO, Profissao profissao) {
        if (profissao == null) {
            profissao = new Profissao();
        }
        profissao.setProfissao(profissaoDTO.getProfissao());
        profissao.setRenda(profissaoDTO.getRenda());
        return profissao;
    }

    private static Telefone buildTelefone(TelefoneDTO telefoneDTO, Telefone telefone) {
        if (telefone == null) {
            telefone = new Telefone();
        }
        telefone.setDdd(telefoneDTO.getDdd());
        telefone.setNumero(telefoneDTO.getNumero());
        telefone.setTipo(telefoneDTO.getTipo());
        return telefone;
    }

    private static Endereco buildEndereco(EnderecoDTO enderecoDTO, Endereco endereco) {
        if (endereco == null) {
            endereco = new Endereco();
        }
        endereco.setCep(enderecoDTO.getCep());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setEstado(enderecoDTO.getEstado());
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setNumero(enderecoDTO.getNumero());
        return endereco;
    }

    public static PropostaAutoDTO fromEntityResumo(PropostaAuto propostaAuto) {
        return PropostaAutoDTO.builder()
                .id(propostaAuto.getProposta())
                .status(buildStatusDTO(propostaAuto.getStatus()))
                .produto(propostaAuto.getProduto())
                .dataCadastro(propostaAuto.getDataCadastro())
                .dataAtualizacao(propostaAuto.getDataAtualizacao())
                .inicioAnalise(propostaAuto.getInicioAnalise())
                .fimAnalise(propostaAuto.getFimAnalise())
                .build();
    }

    public static PropostaAutoDTO fromEntity(PropostaAuto propostaAuto) {
        return PropostaAutoDTO.builder()
                .id(propostaAuto.getProposta())
                .usuarioDTO(buildUsuarioDTO(propostaAuto.getUsuarioCadastro()))
                .dataCadastro(LocalDateTime.now())
                .produto(propostaAuto.getProduto())
                .status(buildStatusDTO(propostaAuto.getStatus()))
                .valor(propostaAuto.getValor())
                .clienteDTO(buildClienteDTO(propostaAuto.getCliente()))
                .garantiaDTO(buildGarantiaDTO(propostaAuto.getGarantia()))
                .build();
    }

    private static ClienteDTO buildClienteDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .rg(cliente.getRg())
                .dataNascimento(cliente.getDataNascimento())
                .email(cliente.getEmail())
                .nomePai(cliente.getNomePai())
                .nomeMae(cliente.getNomeMae())
                .enderecoDTO(buildEnderecoDTO(cliente.getEndereco()))
                .telefoneDTO(buildTelefoneDTO(cliente.getTelefone()))
                .profissaoDTO(buildProfissaoDTO(cliente.getProfissao()))
                .build();
    }

    private static TelefoneDTO buildTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .tipo(telefone.getTipo())
                .build();
    }

    private static EnderecoDTO buildEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .estado(endereco.getEstado())
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .build();
    }

    private static ProfissaoDTO buildProfissaoDTO(Profissao profissao) {
        return ProfissaoDTO.builder()
                .id(profissao.getId())
                .profissao(profissao.getProfissao())
                .renda(profissao.getRenda())
                .build();
    }

    private static GarantiaDTO buildGarantiaDTO(Garantia garantia) {
        return GarantiaDTO.builder()
                .id(garantia.getId())
                .marca(garantia.getMarca())
                .anoFabricacao(garantia.getAnoFabricacao())
                .anoModelo(garantia.getAnoModelo())
                .valor(garantia.getValor())
                .modelo(garantia.getModelo())
                .build();
    }

    private static StatusDTO buildStatusDTO(StatusProposta statusProposta) {
        return StatusDTO.builder()
                .id(statusProposta.getCodigo())
                .descricao(statusProposta.getDescricao())
                .build();
    }

    private static UsuarioDTO buildUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getCodigo())
                .nome(usuario.getNome())
                .build();
    }
}
