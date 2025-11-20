/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package main.java.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.java.logic.ControladorReservas;
import main.java.logic.Reservacion;

/**
 * VentanaFactura permite consultar y visualizar la factura generada
 * para una reservación en el sistema. El usuario ingresa un ID de 
 * reservación y la ventana muestra la información detallada si existe.
 */
public class VentanaFactura extends JFrame {

    // Controlador principal que maneja las operaciones de reservas
    private ControladorReservas controlador;

    // Componentes de interfaz
    private JTextField txtId;
    private JTextArea txtFactura;
    private JButton btnBuscar;
    private JButton btnAtras;

    /**
     * Constructor de la ventana que recibe un controlador para manipular
     * la lógica de las reservaciones.
     * 
     * @param controlador instancia de ControladorReservas
     */
    public VentanaFactura(ControladorReservas controlador) {
        this.controlador = controlador;

        setTitle("Ver Factura");
        setSize(450, 380);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inicializarComponentes();
        eventos();
    }

    /**
     * Método encargado de inicializar todos los componentes gráficos
     * (labels, botones, cajas de texto, textarea).
     */
    private void inicializarComponentes() {
        setLayout(null);

        // Botón para volver al menú principal
        btnAtras = new JButton("Atrás");
        btnAtras.setBounds(40, 95, 100, 30); 
        add(btnAtras);
        btnAtras.addActionListener(e -> {
            new MenuPrincipal(controlador).setVisible(true);
            dispose();
        });

        // Título
        JLabel lblTitulo = new JLabel("Consulta de Factura");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBounds(120, 10, 300, 30);
        add(lblTitulo);

        // Label ID de reservación
        JLabel lblId = new JLabel("ID de reservación:");
        lblId.setBounds(20, 60, 200, 25);
        add(lblId);

        // Campo donde el usuario ingresa el ID
        txtId = new JTextField();
        txtId.setBounds(150, 60, 200, 25);
        add(txtId);

        // Botón para buscar la factura
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(150, 95, 200, 30);
        add(btnBuscar);

        // Área donde se muestra la factura
        txtFactura = new JTextArea();
        txtFactura.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtFactura);
        scroll.setBounds(20, 140, 390, 180);
        add(scroll);
        
        btnAtras = new JButton("Atrás");
    }

    /**
     * Método que configura los listeners (eventos) de los botones:
     * - Buscar: obtiene el ID, consulta la reservación y genera la factura.
     */
    private void eventos() {

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Obtener ID ingresado
                String id = txtId.getText().trim();

                if (id.isBlank()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Debe ingresar un ID válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                // Buscar reservación
                Reservacion r = controlador.buscarReservacion(id);

                if (r == null) {
                    txtFactura.setText("No existe una reservación con ese ID.");
                    return;
                }

                // Construcción del texto de la factura
                StringBuilder sb = new StringBuilder();
                sb.append("----- FACTURA -----\n\n");
                sb.append("ID Reservación: ").append(r.getIdReservacion()).append("\n");
                sb.append("Vuelo: ").append(r.getCodigoVuelo()).append("\n");
                sb.append("Pasajero: ").append(r.getPasajero().getNombre()).append("\n");
                sb.append("Cédula: ").append(r.getPasajero().getCedula()).append("\n");
                sb.append("Asientos: ").append(String.join(", ", r.getIdAsiento())).append("\n");
                sb.append("\nFecha: ").append(r.getFechaCreacion()).append("\n");

                // Cálculo del monto (ejemplo sencillo)
                sb.append("\nMonto Total: ₡").append(r.getIdAsiento().length * 25000);

                txtFactura.setText(sb.toString());
            }
        });
    }
}