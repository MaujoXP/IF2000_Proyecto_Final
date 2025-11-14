/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mauri, alvin, ariana, wendoly
 */
public class Vuelo {
    
    private String codigoVuelo;
    private String origenVuelo;
    private String destinoVuelo;
    private LocalDateTime salidaVuelo;
    private LocalDateTime llegadaVuelo;
    private Avion avion;

    public Vuelo(String codigoVuelo, String origenVuelo, String destinoVuelo, LocalDateTime salidaVuelo, LocalDateTime llegadaVuelo, Avion avion) {
        this.codigoVuelo = codigoVuelo;
        this.origenVuelo = origenVuelo;
        this.destinoVuelo = destinoVuelo;
        this.salidaVuelo = salidaVuelo;
        this.llegadaVuelo = llegadaVuelo;
        this.avion = avion;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public String getOrigenVuelo() {
        return origenVuelo;
    }

    public String getDestinoVuelo() {
        return destinoVuelo;
    }

    public LocalDateTime getSalidaVuelo() {
        return salidaVuelo;
    }

    public LocalDateTime getLlegadaVuelo() {
        return llegadaVuelo;
    }

    public Avion getAvion() {
        return avion;
    }
    
    public void reservarAsientos(String idAsiento) throws Exception{
        if(avion == null){
            throw new Exception("El vuelo " + codigoVuelo + " no posee un avion asignado");
        }
        avion.reservarAsientos(idAsiento);
    }
    
     public void liberarAsentos(String idAsiento) throws Exception{
        if(avion == null){
            throw new Exception("El vuelo " + codigoVuelo + " no posee un avion asignado");
        }
        avion.liberarAsientos(idAsiento);
    }
    
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
    
    public int disponiblesPorClase(String tipoClase){
        // validar clase
        if(tipoClase == null) return 0;
        String tipoClaseNormalizada = tipoClase.trim();
        if(tipoClaseNormalizada.isEmpty()) return 0;
        
        // validar existencia del avion
        if(avion == null) return 0;
        
        // recorrido manueal de asientos
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
