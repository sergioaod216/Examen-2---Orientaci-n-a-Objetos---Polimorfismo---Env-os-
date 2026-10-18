package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import fabricas.EnvioFactory;
import modelos.DatosEnvio;
import modelos.Envio;

public class EnvioServicio implements ServicioEnvios {
    private final List<Envio> envios = new ArrayList<>();
    private final Map<String, EnvioFactory> fabricas;

    public EnvioServicio(Map<String, EnvioFactory> fabricas) {
        this.fabricas = fabricas;
    }

    @Override
    public Envio agregar(String tipo, DatosEnvio datos) {
        if (datos == null || buscar(datos.codigo()) != null) {
            return null; // No se permiten codigos repetidos.
        }

        EnvioFactory fabrica = fabricas.get(tipo);
        if (fabrica == null) {
            return null;
        }

        // La fabrica crea el objeto. El servicio solo lo almacena.
        try {
            Envio envio = fabrica.crearEnvio(datos);
            if (envio != null) {
                envios.add(envio);
            }
            return envio;
        } catch (IllegalArgumentException error) {
            return null;
        }
    }

    @Override
    public Envio buscar(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Envio envio : envios) {
            if (envio.getCodigo().equals(codigo.trim())) {
                return envio;
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(String codigo) {
        Envio envio = buscar(codigo);
        if (envio != null) {
            envios.remove(envio);
            return true;
        }
        return false;
    }

    @Override
    public List<Envio> listar() {
        return new ArrayList<>(envios);
    }

    @Override
    public String[] getTipos() {
        return fabricas.keySet().toArray(new String[0]);
    }
}
