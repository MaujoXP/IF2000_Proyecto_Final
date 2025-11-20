/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

/**
 * Representa un asiento dentro de un avión, el cual contiene un ID único, 
 * un tipo de clase (por ejemplo Ejecutiva o Económica) y un estado para 
 * indicar si está reservado o disponible.
 * 
 * Funcionalidades:
 * - reservarAsiento(): marca el asiento como reservado.
 * - liberarAsiento(): lo deja en estado libre.
 * - isReservado(): consulta si el asiento está ocupado.
 * 
 * @author alvin, mau, ariana, wendoly
 */
public class Asiento {
    
    private String idAsiento;
    private String tipoClase;
    private boolean reservado;
    
    /**
     * Constructor del asiento. Establece el ID del asiento y 
     * el tipo de clase. Si alguno de los valores viene nulo, 
     * se reemplaza por una cadena vacía para evitar errores.
     * 
     * @param idAsiento identificador único del asiento (ej. "1A").
     * @param tipoClase tipo de clase asociada al asiento (Ejecutiva/Económica).
     */
    public Asiento(String idAsiento, String tipoClase){
        this.idAsiento = (idAsiento == null) ? "" : idAsiento.trim();
        this.tipoClase = (tipoClase == null) ? "" : tipoClase.trim();
        this.reservado = false;
    }

    /**
     * Obtiene el ID del asiento.
     * @return cadena con el identificador del asiento.
     */
    public String getIdAsiento() {
        return idAsiento;
    }

    /**
     * Obtiene el tipo de clase del asiento.
     * @return tipo de clase como texto.
     */
    public String getTipoClase() {
        return tipoClase;
    }

    /**
     * Indica si el asiento está reservado.
     * @return true si está ocupado, false si está libre.
     */
    public boolean isReservado() {
        return reservado;
    }
    
    /**
     * Marca el asiento como reservado.
     */
    public void reservarAsiento(){
        this.reservado = true;
    }
    
    /**
     * Libera el asiento, marcándolo como disponible.
     */
    public void liberarAsiento(){
        this.reservado = false;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Asiento: ").append(idAsiento).append("\n");
        sb.append("Clase asignada: ").append(tipoClase).append("\n");
        sb.append("Estado del asiento: ").append(reservado ? "Ocupado" : "Libre").append("\n");
        return sb.toString();
    }
}
