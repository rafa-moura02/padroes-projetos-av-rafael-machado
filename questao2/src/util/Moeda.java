package util;

import java.util.Locale;

/** Formatacao de valores monetarios em reais. */
public final class Moeda {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private Moeda() {
    }

    public static String formatar(double valor) {
        return String.format(PT_BR, "R$ %,.2f", valor);
    }
}
