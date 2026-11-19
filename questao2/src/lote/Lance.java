package lote;

import java.time.LocalDateTime;
import observador.Licitante;

public record Lance(Licitante licitante, double valor, LocalDateTime dataHora) {
}
