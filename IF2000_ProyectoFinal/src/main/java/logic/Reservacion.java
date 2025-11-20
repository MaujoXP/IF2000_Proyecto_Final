/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.logic;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import main.java.domain.Pasajero;
import main.java.domain.Vuelo;

/**
 * Clase que representa una reservación de vuelo, incluyendo el pasajero,
 * los asientos reservados, el código del vuelo y la fecha de creación.
 * También gestiona la reserva y cancelación de asientos en un vuelo.
 * 
 * Genera un identificador único con un contador incremental.
 * 
 * @author mauri, alvin, ariana, wendoly
 */
public class Reservacion {
    
    /** Identificador único de la reservación */
    private String idReservacion;

    /** Código del vuelo asociado a la reservación */
    private String codigoVuelo;

    /** Objeto pasajero dueño de la reservación */
    private Pasajero pasajero;

    /** Lista de IDs de los asientos reservados */
    private String[] idAsiento;

    /** Fecha y hora de creación de la reservación */
    private LocalDateTime fechaCreacion;
    
    /** Contador para generar IDs únicos */
    private static int contador = 0; // generador de id unicos

    /**
     * Constructor de la clase Reservacion.
     *
     * @param codigoVuelo Código del vuelo que se está reservando
     * @param pasajero Pasajero que realiza la reservación
     * @param idAsiento Arreglo con los IDs de los asientos reservados
     * @param fechaCreacion Fecha de creación de la reservación
     */
    public Reservacion(String codigoVuelo, Pasajero pasajero, String[] idAsiento, LocalDateTime fechaCreacion) {
        this.codigoVuelo = codigoVuelo;
        this.pasajero = pasajero;
        this.idAsiento = (idAsiento == null) ? new String[0] : idAsiento;
        this.fechaCreacion = fechaCreacion;
        contador++;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
       this.idReservacion = "R-" + String.format("%03d", contador);


    }

    /**
     * Obtiene el identificador de la reservación.
     * @return ID de la reservación
     */
    public String getIdReservacion() {
        return idReservacion;
    }

    /**
     * Obtiene el código del vuelo asociado.
     * @return Código del vuelo
     */
    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    /**
     * Obtiene el pasajero asociado a la reservación.
     * @return Pasajero registrado
     */
    public Pasajero getPasajero() {
        return pasajero;
    }

    /**
     * Obtiene los asientos reservados.
     * @return Arreglo de IDs de asientos
     */
    public String[] getIdAsiento() {
        return idAsiento;
    }

    /**
     * Obtiene la fecha de creación de la reservación.
     * @return Fecha y hora de creación
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    /**
     * Realiza la reserva de los asientos en un vuelo.
     * Aplica un rollback si ocurre algún error durante la reserva.
     *
     * @param vuelo Vuelo en el que se intenta reservar
     * @throws Exception Si el vuelo no existe o si no se pueden reservar los asientos
     */
    public void reservarVuelo(Vuelo vuelo) throws Exception{
        if(vuelo == null){
            throw new Exception("No es posible reservar, vuelo no existente");
        }
        
        //rollback por si sale algo mal
        String[] reservacionTemporal = new String[idAsiento.length];
        int cantidadReservaciones = 0;
        
        try {
            for(String idAsiento : idAsiento){
                vuelo.reservarAsientos(idAsiento);
                reservacionTemporal[cantidadReservaciones++] = idAsiento;
            }
        } catch (Exception e) {
            
            //rollback
            for(int i = 0; i < cantidadReservaciones; i++){
                try{
                    vuelo.liberarAsentos(reservacionTemporal[i]);
                } catch (Exception ignored){}
            }
            
            throw new Exception("No se ha logrado efectuar la reservacion: " + e.getMessage());
        }
    }
    
    /**
     * Cancela una reservación liberando los asientos asociados.
     *
     * @param vuelo Vuelo del cual se liberan los asientos
     * @throws Exception Si el vuelo no existe
     */
    public void cancelaReservacionVuelo(Vuelo vuelo) throws Exception{
        if(vuelo == null){
            throw new Exception("Vuelo no existe, no es posible cancelar");
        }
        for(String idAsiento : idAsiento){
            try{
                vuelo.liberarAsentos(idAsiento);
            } catch (Exception ignored){} // si ya estaba libre lo ignora
        }
    }
    
    /**
     * Representación en texto de todos los datos de la reservación.
     * @return Cadena con la información completa de la reservación
     */
    @Override
    public String toString(){
    StringBuilder sb = new StringBuilder();
    
    sb.append("----- Datos de la reservación -----");
    sb.append("ID de la reservacion: ").append(idReservacion).append("\n");
    sb.append("Codigo del vuelo; ").append(codigoVuelo).append("\n");
    
    sb.append("---------------------\n");
    sb.append("Pasajero: \n");
    sb.append(pasajero == null ? "No asignado\n" : pasajero.toString() + "\n");
    
    sb.append("---------------------\n");
    sb.append("Asientos reservados: \n");
    if(idAsiento.length == 0){
        sb.append(" (Ninguno)\n");
    }else{
        for(String id : idAsiento){
            sb.append(" - ").append(id).append("\n");
        }
    }
    
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    sb.append("---------------------\n");
    sb.append("Fecha de la creación: ").append(fechaCreacion.format(formato)).append("\n");
    sb.append("---------------------\n");
    
    return sb.toString();
    
    }

    /**
     * Método no implementado para obtener el vuelo asociado.
     * @return Nada, siempre lanza excepción
     * @throws UnsupportedOperationException Siempre, ya que no está implementado
     */
    public Object getVuelo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}