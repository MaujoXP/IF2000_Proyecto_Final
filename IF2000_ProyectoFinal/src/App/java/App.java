/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App.java;

import java.time.LocalDateTime;
import main.java.logic.ControladorReservas;
import main.java.domain.*;
import main.java.ui.MenuPrincipal;

public class App {
    public static void main(String[] args) {

        ControladorReservas controlador = new ControladorReservas();

        // Crear aviones
        Avion avion1 = new Avion("ABC123");
        Avion avion2 = new Avion("XYZ987");

        // Crear vuelos
        Vuelo vuelo1 = new Vuelo(
                "CR100",
                "San José",
                "Panamá",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                avion1
        );

        Vuelo vuelo2 = new Vuelo(
                "CR200",
                "San José",
                "México",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(3),
                avion2
        );

        controlador.agregarVuelo(vuelo1);
        controlador.agregarVuelo(vuelo2);

        // Abrir interfaz gráfica (Swing)
        new MenuPrincipal(controlador).setVisible(true);
    }
}


