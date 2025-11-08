package br.com.example.proposal.api.database.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter()
public enum PropostaStatus {
    AGUARDANDO_ANALISE(1L, "Aguardando Analise"), EM_ANALISE(2L, "Em Analise"),
    APROVADA(3L, "Aprovada"), RECUSADA(4L, "Recusada");

    private final Long codigo;
    private String descricao;

    PropostaStatus(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static PropostaStatus fromCodigo(Long codigo) {
        if (codigo == null)
            return null;

        return Arrays.stream(values())
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}
