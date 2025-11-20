package main.java.logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import main.java.domain.Vuelo;
import main.java.domain.Pasajero;

/**
 * Clase encargada de gestionar todo el sistema de reservas.
 * Administra la creación, búsqueda y cancelación de reservaciones,
 * así como el registro de vuelos y pasajeros.
 *
 * Actúa como capa lógica central para conectar las operaciones
 * entre vuelos, pasajeros y reservaciones.
 * 
 * @author mauri, alvin, ariana, wendoly
 */
public class ControladorReservas {

    /** Lista de vuelos registrados en el sistema */
    private List<Vuelo> vuelos;

    /** Lista de pasajeros registrados en el sistema */
    private List<Pasajero> pasajeros;

    /** Lista de reservaciones realizadas */
    private List<Reservacion> reservaciones;

    /**
     * Constructor del controlador.
     * Inicializa las listas de vuelos, pasajeros y reservaciones.
     */
    public ControladorReservas() {
        this.vuelos = new ArrayList<>();
        this.pasajeros = new ArrayList<>();
        this.reservaciones = new ArrayList<>();
    }

    /**
     * Agrega un vuelo a la lista del sistema.
     *
     * @param vuelo Objeto vuelo a registrar
     */
    public void agregarVuelo(Vuelo vuelo) {
        vuelos.add(vuelo);
    }

    /**
     * Agrega un pasajero al sistema.
     *
     * @param pasajero Objeto pasajero a registrar
     */
    public void agregarPasajero(Pasajero pasajero) {
        pasajeros.add(pasajero);
    }

    /**
     * Obtiene la lista completa de vuelos.
     *
     * @return Lista de objetos Vuelo
     */
    public List<Vuelo> listarVuelos() {
        return vuelos;
    }

    /**
     * Obtiene la lista de reservaciones realizadas.
     *
     * @return Lista de reservaciones
     */
    public List<Reservacion> listarReservaciones() {
        return reservaciones;
    }

    /**
     * Busca un vuelo por su código.
     *
     * @param codigo Código del vuelo a buscar
     * @return Vuelo encontrado o null si no existe
     */
    public Vuelo buscarVuelo(String codigo) {
        for (Vuelo v : vuelos) {
            if (v.getCodigoVuelo().equalsIgnoreCase(codigo)) {
                return v;
            }
        }
        return null; // si no existe
    }

    /**
     * Crea una nueva reservación para un pasajero en un vuelo específico.
     *
     * @param codigoVuelo Código del vuelo donde se reservará
     * @param pasajero Pasajero que realiza la reservación
     * @param idAsientos Arreglo de IDs de asientos a reservar
     * @return La reservación creada
     * @throws Exception Si el vuelo no existe o falla la reserva
     */
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

    /**
     * Busca una reservación mediante su ID.
     *
     * @param idReservacion ID de la reservación
     * @return La reservación encontrada o null si no existe
     */
    public Reservacion buscarReservacion(String idReservacion) {
        for (Reservacion r : reservaciones) {
            if (r.getIdReservacion().equals(idReservacion)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Cancela una reservación existente.
     * Libera sus asientos asociados en el vuelo correspondiente.
     *
     * @param idReservacion ID de la reservación a cancelar
     * @throws Exception Si la reservación o el vuelo no existen
     */
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