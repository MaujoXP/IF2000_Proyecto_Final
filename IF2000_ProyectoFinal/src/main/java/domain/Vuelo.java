/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa un vuelo dentro del sistema. 
 * Contiene información del código, origen, destino, horarios y el avión asignado.
 * Permite reservar asientos, liberar asientos, consultar información específica
 * y obtener detalles del vuelo.
 * 
 * @author mauri, alvin, ariana, wendoly
 */
public class Vuelo {
    
    /** Código único del vuelo (ej: "CR123"). */
    private String codigoVuelo;
    
    /** Ciudad o aeropuerto de salida. */
    private String origenVuelo;
    
    /** Ciudad o aeropuerto de destino. */
    private String destinoVuelo;
    
    /** Fecha y hora de salida del vuelo. */
    private LocalDateTime salidaVuelo;
    
    /** Fecha y hora de llegada del vuelo. */
    private LocalDateTime llegadaVuelo;
    
    /** Avión asignado a este vuelo. */
    private Avion avion;

    /**
     * Constructor de la clase Vuelo.
     *
     * @param codigoVuelo Código único del vuelo
     * @param origenVuelo Origen del vuelo
     * @param destinoVuelo Destino del vuelo
     * @param salidaVuelo Fecha y hora de salida
     * @param llegadaVuelo Fecha y hora de llegada
     * @param avion Avión asignado
     */
    public Vuelo(String codigoVuelo, String origenVuelo, String destinoVuelo, 
                 LocalDateTime salidaVuelo, LocalDateTime llegadaVuelo, Avion avion) {
        this.codigoVuelo = codigoVuelo;
        this.origenVuelo = origenVuelo;
        this.destinoVuelo = destinoVuelo;
        this.salidaVuelo = salidaVuelo;
        this.llegadaVuelo = llegadaVuelo;
        this.avion = avion;
    }

    /** @return código del vuelo. */
    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    /** @return ciudad o aeropuerto de origen. */
    public String getOrigenVuelo() {
        return origenVuelo;
    }

    /** @return ciudad o aeropuerto de destino. */
    public String getDestinoVuelo() {
        return destinoVuelo;
    }

    /** @return fecha y hora de salida. */
    public LocalDateTime getSalidaVuelo() {
        return salidaVuelo;
    }

    /** @return fecha y hora de llegada. */
    public LocalDateTime getLlegadaVuelo() {
        return llegadaVuelo;
    }

    /** @return avión asignado al vuelo. */
    public Avion getAvion() {
        return avion;
    }
    
    /**
     * Intenta reservar un asiento dentro del avión asociado al vuelo.
     *
     * @param idAsiento Identificador del asiento (ej: "A1")
     * @throws Exception si el vuelo no tiene avión o si el asiento no está disponible
     */
    public void reservarAsientos(String idAsiento) throws Exception{
        if(avion == null){
            throw new Exception("El vuelo " + codigoVuelo + " no posee un avion asignado");
        }
        avion.reservarAsientos(idAsiento);
    }
    
    /**
     * Libera (des-reserva) un asiento del avión.
     *
     * @param idAsiento Identificador del asiento
     * @throws Exception si el vuelo no tiene avión asociado
     */
    public void liberarAsentos(String idAsiento) throws Exception{
        if(avion == null){
            throw new Exception("El vuelo " + codigoVuelo + " no posee un avion asignado");
        }
        avion.liberarAsientos(idAsiento);
    }
    
    /**
     * Devuelve información detallada de un asiento específico.
     *
     * @param idAsiento ID del asiento a consultar
     * @return Texto con la información del asiento o null si no existe
     */
    public String obtenerInfoAsiento(String idAsiento){
        // validacion basica de entrada
        if(idAsiento == null){
            return null;
        }
        
        //validar avion asociado al vuelo
        if(avion == null){
            return null;
        }
        
        //normalizacion del idAsiento
        String claveAsiento = idAsiento.trim().toUpperCase();
        if(claveAsiento.isEmpty()){
            return null;
        }
        
        // buscar el asiento en el avion
        Asiento asiento = avion.buscarAsientos(claveAsiento);
        if(asiento == null){
            return null;
        }
        
        // creacion del formato de texto a devolver
        StringBuilder sb = new StringBuilder();
        sb.append("----- Informacion del asiento -----\n");
        sb.append("Vuelo: ").append(codigoVuelo).append("|").append(origenVuelo)
                .append(" -> ").append(destinoVuelo).append("\n");
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
        if(salidaVuelo != null)sb.append("Salida del vuelo: ").append(salidaVuelo.format(formato)).append("\n");
        if(llegadaVuelo != null)sb.append("Llegada del vuelo: ").append(llegadaVuelo.format(formato)).append("\n");
        
        sb.append("--------------------------------------\n");
        
        return sb.toString();
    }
    
    /**
     * Cuenta cuántos asientos disponibles existen según la clase solicitada.
     *
     * @param tipoClase Clase de asiento ("Economica", "Ejecutiva", etc.)
     * @return cantidad de asientos disponibles en esa clase
     */
    public int disponiblesPorClase(String tipoClase){
        // validar clase
        if(tipoClase == null) return 0;
        String tipoClaseNormalizada = tipoClase.trim();
        if(tipoClaseNormalizada.isEmpty()) return 0;
        
        // validar existencia del avion
        if(avion == null) return 0;
        
        int contador = 0;
        Asiento[] listaAsientos = avion.getAsiento();
        if(listaAsientos == null) return 0;
        
        for(int i = 0; i < listaAsientos.length; i++){
            Asiento actual = listaAsientos[i];
            if(actual == null) continue;
            
            String claseAsiento = (actual.getTipoClase() == null) ? "" : actual.getTipoClase().trim();
            if(claseAsiento.equalsIgnoreCase(tipoClaseNormalizada)){
                if(!actual.isReservado()){
                    contador++;
                }
            }
        }
        return contador;
    }
    
    /**
     * Devuelve información formateada del vuelo.
     *
     * @return String con datos del vuelo
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("----- Informacion del vuelo -----\n");
        sb.append("Codigo del vuelo: ").append(codigoVuelo).append("\n");
        sb.append("Origen del vuelo: ").append(origenVuelo).append("\n");
        sb.append("Destino del vuelo: ").append(destinoVuelo).append("\n");
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM");
        if(salidaVuelo != null)sb.append("Salida del vuelo: ").append(salidaVuelo.format(formato)).append("\n");
        if(llegadaVuelo != null)sb.append("Llegada del vuelo: ").append(llegadaVuelo.format(formato)).append("\n");
        
        sb.append("Avion: ").append(avion == null ? "N/A" : avion.getMatriculaAvion()).append("\n");
        sb.append("-------------------------------------\n");
        
        return sb.toString();
        
    }
}