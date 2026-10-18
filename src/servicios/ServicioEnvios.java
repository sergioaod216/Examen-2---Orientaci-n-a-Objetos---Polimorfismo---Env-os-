package servicios;

import java.util.List;
import modelos.DatosEnvio;
import modelos.Envio;

// Operaciones que puede solicitar el controlador.
public interface ServicioEnvios {
    Envio agregar(String tipo, DatosEnvio datos);
    Envio buscar(String codigo);
    boolean eliminar(String codigo);
    List<Envio> listar();
    String[] getTipos();
}
