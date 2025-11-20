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
 *
 * @author mauri, alvin, ariana, wendoly
 */
public class Reservacion {
    
    private String idReservacion;
    private String codigoVuelo;
    private Pasajero pasajero;
    private String[] idAsiento;
    private LocalDateTime fechaCreacion;
    
    private static int contador = 0; // generador de id unicos

    public Reservacion(String codigoVuelo, Pasajero pasajero, String[] idAsiento, LocalDateTime fechaCreacion) {
        this.codigoVuelo = codigoVuelo;
        this.pasajero = pasajero;
        this.idAsiento = (idAsiento == null) ? new String[0] : idAsiento;
        this.fechaCreacion = fechaCreacion;
        contador++;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        this.idReservacion = "Reservacion- " + fechaCreacion.format(formato) + "- " + String.format("%03d", contador);
    }

    public String getIdReservacion() {
        return idReservacion;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public String[] getIdAsiento() {
        return idAsiento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    // reserva del vuelo
    
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
    
    //cancelacion de la reservacion
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

    public Object getVuelo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    
