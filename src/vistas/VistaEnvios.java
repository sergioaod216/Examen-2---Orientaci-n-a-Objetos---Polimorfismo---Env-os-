package vistas;

import java.awt.event.ActionListener;
import java.util.List;
import modelos.Envio;

// El controlador usa estos metodos sin depender de una ventana concreta.
public interface VistaEnvios {
    String getCodigo();
    String getCliente();
    String getPeso();
    String getDistancia();
    String getTipoEnvioSeleccionado();
    void setTiposEnvio(String[] tipos);
    void setGuardarEnvioClick(ActionListener evento);
    void setEliminarEnvioClick(ActionListener evento);
    void mostrarEnvios(List<Envio> envios);
    void mostrarMensaje(String mensaje);
    String pedirCodigo();
    void limpiarCampos();
    void ocultarEdicionEnvio();
    void mostrarVentana();
}
