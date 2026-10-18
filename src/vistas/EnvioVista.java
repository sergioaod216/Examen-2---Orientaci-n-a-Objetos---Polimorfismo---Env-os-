package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelos.Envio;

public class EnvioVista extends JFrame implements VistaEnvios {
    private final JTable tablaEnvios;
    private final JPanel panelEdicion;
    private final JTextField txtCodigo;
    private final JTextField txtCliente;
    private final JTextField txtPeso;
    private final JTextField txtDistancia;
    private final JComboBox<String> cmbTipoEnvio;
    private final JButton btnGuardar;
    private final JButton btnRetirar;

    public EnvioVista() {
        setTitle("Operador Logístico");
        setSize(760, 470);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToolBar barra = new JToolBar();
        barra.setFloatable(false);

        JButton btnAgregar = new JButton();
        agregarIcono(btnAgregar, "/iconos/AgregarEnvio.png", "Agregar envío");
        btnAgregar.addActionListener(evento -> mostrarFormulario());
        barra.add(btnAgregar);

        btnRetirar = new JButton();
        agregarIcono(btnRetirar, "/iconos/QuitarEnvio.png", "Retirar envío");
        barra.add(btnRetirar);

        panelEdicion = new JPanel(null);
        panelEdicion.setPreferredSize(new Dimension(740, 115));

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(10, 10, 100, 25);
        panelEdicion.add(lblCodigo);
        txtCodigo = new JTextField();
        txtCodigo.setBounds(110, 10, 150, 25);
        panelEdicion.add(txtCodigo);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(285, 10, 100, 25);
        panelEdicion.add(lblTipo);
        cmbTipoEnvio = new JComboBox<>();
        cmbTipoEnvio.setBounds(395, 10, 160, 25);
        panelEdicion.add(cmbTipoEnvio);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 42, 100, 25);
        panelEdicion.add(lblCliente);
        txtCliente = new JTextField();
        txtCliente.setBounds(110, 42, 150, 25);
        panelEdicion.add(txtCliente);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(285, 42, 110, 25);
        panelEdicion.add(lblDistancia);
        txtDistancia = new JTextField();
        txtDistancia.setBounds(395, 42, 160, 25);
        panelEdicion.add(txtDistancia);

        JLabel lblPeso = new JLabel("Peso en Kg");
        lblPeso.setBounds(10, 74, 100, 25);
        panelEdicion.add(lblPeso);
        txtPeso = new JTextField();
        txtPeso.setBounds(110, 74, 150, 25);
        panelEdicion.add(txtPeso);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(285, 74, 130, 25);
        panelEdicion.add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(425, 74, 130, 25);
        btnCancelar.addActionListener(evento -> ocultarEdicionEnvio());
        panelEdicion.add(btnCancelar);
        panelEdicion.setVisible(false);

        tablaEnvios = new JTable();
        JScrollPane listado = new JScrollPane(tablaEnvios);

        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.add(barra, BorderLayout.NORTH);
        parteSuperior.add(panelEdicion, BorderLayout.CENTER);
        add(parteSuperior, BorderLayout.NORTH);
        add(listado, BorderLayout.CENTER);
    }

    private void agregarIcono(JButton boton, String nombre, String texto) {
        URL imagen = getClass().getResource(nombre);
        if (imagen != null) {
            boton.setIcon(new ImageIcon(imagen));
        } else {
            boton.setText(texto);
        }
        boton.setToolTipText(texto);
    }

    private void mostrarFormulario() {
        panelEdicion.setVisible(true);
        panelEdicion.revalidate();
    }

    @Override
    public String getCodigo() {
        return txtCodigo.getText();
    }

    @Override
    public String getCliente() {
        return txtCliente.getText();
    }

    @Override
    public String getPeso() {
        return txtPeso.getText();
    }

    @Override
    public String getDistancia() {
        return txtDistancia.getText();
    }

    @Override
    public String getTipoEnvioSeleccionado() {
        return (String) cmbTipoEnvio.getSelectedItem();
    }

    @Override
    public void setTiposEnvio(String[] tipos) {
        cmbTipoEnvio.setModel(new DefaultComboBoxModel<>(tipos));
    }

    @Override
    public void setGuardarEnvioClick(ActionListener evento) {
        btnGuardar.addActionListener(evento);
    }

    @Override
    public void setEliminarEnvioClick(ActionListener evento) {
        btnRetirar.addActionListener(evento);
    }

    @Override
    public void mostrarEnvios(List<Envio> envios) {
        String[] encabezados = {"Código", "Cliente", "Tipo", "Peso (kg)", "Distancia (km)", "Tarifa"};
        DefaultTableModel tabla = new DefaultTableModel(encabezados, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        for (Envio envio : envios) {
            // Java llama al calcularTarifa() del tipo real de envio.
            tabla.addRow(new Object[] {
                envio.getCodigo(), envio.getCliente(), envio.getTipo(),
                formato.format(envio.getPeso()), formato.format(envio.getDistancia()),
                "$ " + formato.format(envio.calcularTarifa())
            });
        }
        tablaEnvios.setModel(tabla);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public String pedirCodigo() {
        return JOptionPane.showInputDialog(this, "Código del envío que desea retirar:");
    }

    @Override
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
    }

    @Override
    public void ocultarEdicionEnvio() {
        panelEdicion.setVisible(false);
    }

    @Override
    public void mostrarVentana() {
        setVisible(true);
    }
}
