package main.java.logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import main.java.domain.Vuelo;
import main.java.domain.Pasajero;

public class ControladorReservas {

    private List<Vuelo> vuelos;
    private List<Pasajero> pasajeros;
    private List<Reservacion> reservaciones;

    public ControladorReservas() {
        this.vuelos = new ArrayList<>();
        this.pasajeros = new ArrayList<>();
        this.reservaciones = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    public void agregarPasajero(Pasajero pasajero) {
        pasajeros.add(pasajero);
    }

    public List<Vuelo> listarVuelos() {
        return vuelos;
    }

    public List<Reservacion> listarReservaciones() {
        return reservaciones;
    }

    public Vuelo buscarVuelo(String codigo) {
        for (Vuelo v : vuelos) {
            if (v.getCodigoVuelo().equalsIgnoreCase(codigo)) {
                return v;
            }
        }
        return null; // si no existe
    }

    public Reservacion crearReservacion(String codigoVuelo, Pasajero pasajero, String[] idAsientos)
            throws Exception {

        // Buscar vuelo
        Vuelo vuelo = buscarVuelo(codigoVuelo);
        if (vuelo == null) {
            throw new Exception("El vuelo no existe.");
        }

        // Crear objeto reservación
        Reservacion reservacion = new Reservacion(
                codigoVuelo,
                pasajero,
                idAsientos,
                LocalDateTime.now()
        );

        // Intentar reservar asientos
        reservacion.reservarVuelo(vuelo);

        // Guardar en la lista del sistema
        reservaciones.add(reservacion);

        return reservacion;
    }

    public Reservacion buscarReservacion(String idReservacion) {
        for (Reservacion r : reservaciones) {
            if (r.getIdReservacion().equals(idReservacion)) {
                return r;
            }
        }
        return null;
    }

    public void cancelarReservacion(String idReservacion) throws Exception {

        // Buscar la reservación
        Reservacion r = buscarReservacion(idReservacion);

        if (r == null) {
            throw new Exception("No existe una reservación con ese ID.");
        }

        // Buscar vuelo correspondiente
        Vuelo vuelo = buscarVuelo(r.getCodigoVuelo());
        if (vuelo == null) {
            throw new Exception("El vuelo asociado a la reservación ya no existe.");
        }

        // Cancelar reserva en el vuelo
        r.cancelaReservacionVuelo(vuelo);

        // Eliminar de la lista del sistema
        reservaciones.remove(r);
    }
}
