/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

/**
 *
 * @author alvin
 */
public class Asiento {
    
    private String idAsiento;
    private String tipoClase;
    private boolean reservado;
    
    public Asiento(String idAsiento, String tipoClase){
        this.idAsiento = (idAsiento == null) ? "" : idAsiento.trim();
        this.tipoClase = (tipoClase == null) ? "" : tipoClase.trim();
        this.reservado = false;
    }

    public String getIdAsiento() {
        return idAsiento;
    }

    public String getTipoClase() {
        return tipoClase;
    }

    public boolean isReservado() {
        return reservado;
    }
    
    public void reservarAsiento(){
        this.reservado = true;
    }
    
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
