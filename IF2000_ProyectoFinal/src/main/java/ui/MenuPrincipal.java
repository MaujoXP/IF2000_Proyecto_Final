/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.ui;

import javax.swing.*;
import java.awt.*;
import main.java.logic.ControladorReservas;

public class MenuPrincipal extends JFrame {

    private ControladorReservas controlador;

    public MenuPrincipal(ControladorReservas controlador) {
        this.controlador = controlador;

        setTitle("Sistema de Reservación de Vuelos");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Panel de botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnReservar = new JButton("Reservar vuelo");
        JButton btnCancelar = new JButton("Cancelar reserva");
        JButton btnVerFacturas = new JButton("Ver facturas");
        JButton btnSalir = new JButton("Salir");

        panel.add(btnReservar);
        panel.add(btnCancelar);
        panel.add(btnVerFacturas);
        panel.add(btnSalir);

        add(panel, BorderLayout.CENTER);

        // ACCIONES
        btnSalir.addActionListener(e -> System.exit(0));

       btnReservar.addActionListener(e -> {
    VentanaReservacion vr = new VentanaReservacion(controlador);
    vr.setVisible(true);
});


        btnCancelar.addActionListener(e -> {
            VentanaCancelacion vc = new VentanaCancelacion(controlador);
            vc.setVisible(true);
        });

        btnVerFacturas.addActionListener(e -> {
            VentanaFactura ventana = new VentanaFactura(controlador);
            ventana.setVisible(true);
        });
    }
}


                                                                