/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.ui;

/**
 *
 * @author mauri
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.java.logic.ControladorReservas;
import main.java.domain.Pasajero;
import main.java.domain.Vuelo;
import main.java.logic.Reservacion;
import java.util.List;

public class VentanaReservacion extends JFrame {

    private ControladorReservas controlador;

    private JComboBox<String> comboVuelos;
    private JComboBox<String> comboClase;
    private JList<String> listaAsientos;

    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtTelefono;

    private DefaultListModel<String> modeloAsientos;

    private JButton btnReservar;

    public VentanaReservacion(ControladorReservas controlador) {
        this.controlador = controlador;

        setTitle("Sistema de Reservación");
        setSize(520, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inicializarComponentes();
        cargarVuelos();
        eventos();
    }

    private void inicializarComponentes() {

        setLayout(null);

        JLabel lblTitulo = new JLabel("Reservación de Vuelos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(130, 10, 300, 30);
        add(lblTitulo);

        JLabel lblVuelo = new JLabel("Seleccione un vuelo:");
        lblVuelo.setBounds(20, 60, 200, 25);
        add(lblVuelo);

        comboVuelos = new JComboBox<>();
        comboVuelos.setBounds(20, 85, 450, 25);
        add(comboVuelos);

        JLabel lblClase = new JLabel("Clase:");
        lblClase.setBounds(20, 120, 200, 25);
        add(lblClase);

        comboClase = new JComboBox<>(new String[]{"Económica", "Ejecutiva"});
        comboClase.setBounds(20, 145, 200, 25);
        add(comboClase);

        JLabel lblAsientos = new JLabel("Asientos disponibles:");
        lblAsientos.setBounds(20, 185, 200, 25);
        add(lblAsientos);

        modeloAsientos = new DefaultListModel<>();
        listaAsientos = new JList<>(modeloAsientos);
        listaAsientos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scroll = new JScrollPane(listaAsientos);
        scroll.setBounds(20, 210, 200, 200);
        add(scroll);

        JLabel lblDatos = new JLabel("Datos del pasajero:");
        lblDatos.setBounds(250, 185, 200, 25);
        add(lblDatos);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(250, 215, 200, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(250, 240, 200, 25);
        add(txtNombre);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(250, 275, 200, 25);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(250, 300, 200, 25);
        add(txtCedula);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(250, 335, 200, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(250, 360, 200, 25);
        add(txtTelefono);

        btnReservar = new JButton("Confirmar Reserva");
        btnReservar.setBounds(150, 450, 200, 40);
        add(btnReservar);
    }

    private void cargarVuelos() {
        List<Vuelo> vuelos = controlador.listarVuelos();

        comboVuelos.removeAllItems();

        for (Vuelo v : vuelos) {
            comboVuelos.addItem(v.getCodigoVuelo());
        }

        if (!vuelos.isEmpty()) {
            cargarAsientos();
        }
    }

    private void cargarAsientos() {
        modeloAsientos.clear();

        String codigoVuelo = (String) comboVuelos.getSelectedItem();
        Vuelo vuelo = controlador.buscarVuelo(codigoVuelo);

        if (vuelo == null) return;

for (var asiento : vuelo.getAvion().getAsiento()) {
    if (!asiento.isReservado()) {
        modeloAsientos.addElement(asiento.getIdAsiento());
    }
}
    }
    private void eventos() {

        comboVuelos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarAsientos();
            }
        });

        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    realizarReserva();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void realizarReserva() throws Exception {

        // Validaciones
        String codigoVuelo = (String) comboVuelos.getSelectedItem();
        Vuelo vuelo = controlador.buscarVuelo(codigoVuelo);

        if (vuelo == null) {
            throw new Exception("Debe seleccionar un vuelo válido.");
        }

        List<String> seleccion = listaAsientos.getSelectedValuesList();

        if (seleccion.isEmpty()) {
            throw new Exception("Debe seleccionar al menos 1 asiento.");
        }

        if (txtNombre.getText().isBlank() ||
                txtCedula.getText().isBlank() ||
                txtTelefono.getText().isBlank()) {

            throw new Exception("Debe completar todos los datos del pasajero.");
        }

       Pasajero pasajero = new Pasajero(
        txtNombre.getText().trim(),
        txtCedula.getText().trim(),
        "", // correo ausente
        txtTelefono.getText().trim()
);


        String[] arregloAsientos = seleccion.toArray(new String[0]);

        // Crear reservación
        Reservacion r = controlador.crearReservacion(
                codigoVuelo,
                pasajero,
                arregloAsientos
        );

        JOptionPane.showMessageDialog(this,
                "Reserva realizada con éxito.\n\n" +
                        "ID: " + r.getIdReservacion() + "\n" +
                        "Pasajero: " + pasajero.getNombre() + "\n" +
                        "Asientos: " + seleccion,
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        cargarAsientos(); // refrescar lista
    }
}

                    