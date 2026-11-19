package lote;

import java.time.LocalDateTime;
import observador.Licitante;

/** RF02: lance aceito, com participante e valor. */
public record Lance(Licitante licitante, double valor, LocalDateTime dataHora) {
}
