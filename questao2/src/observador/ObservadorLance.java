package observador;

import lote.Lance;
import lote.Lote;

public interface ObservadorLance {
    void atualizar(Lote lote, Lance lance);
}
