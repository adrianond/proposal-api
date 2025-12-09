package br.com.example.proposal.api.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MoedaFormatter {

    public static String formatar(BigDecimal valor) {
        if (valor == null) return null;
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formato.format(valor);
    }

    public static BigDecimal stringParaBigDecimal(String valor) {
        if (valor == null || valor.isBlank()) return null;
        try {
            NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));
            Number number = format.parse(valor);
            return BigDecimal.valueOf(number.doubleValue());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Valor inválido: " + valor, e);
        }
    }
}
