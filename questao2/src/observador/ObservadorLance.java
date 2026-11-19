package observador;

import lote.Lance;
import lote.Lote;

/**
 * Padrao Observer - Observer (RNF01).
 * Qualquer interessado nos lances implementa esta interface; novos tipos de
 * interessados exigem apenas novas classes, sem alterar Lote.
 */
public interface ObservadorLance {
    void atualizar(Lote lote, Lance lance);
}
