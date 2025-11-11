/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

/**
 *
 * @author mauri, alvin, ariana, wendoly
 */
public class Avion {
    
    private String matriculaAvion;
    private Asiento[] asientos;
    
    public Avion(String matriculaAvion){
        this.matriculaAvion = matriculaAvion;
        this.asientos = new Asiento[4];
        asientos[0] = new Asiento("1A", "Ejecutiva");
        asientos[1] = new Asiento("1B", "Ejecutiva");
        asientos[2] = new Asiento("2A", "Economica");
        asientos[3] = new Asiento("2B", "Economica");
    }
    
    public String getMatriculaAvion(){
        return matriculaAvion;
    }
    
    public Asiento[] getAsiento(){
        return asientos;
    }
    
    // corregir estructura
    private Asiento buscarAsientos(String idAsiento){
        if(idAsiento == null || idAsiento.trim().isEmpty()) return null;
        String claveAsiento = idAsiento.trim().toUpperCase();
        for(Asiento a : asientos){
            if(a != null && a.getIdAsiento().toUpperCase().equals(claveAsiento))
                return a;
        }
        return null;
    }
    
    public void reservarAsientos(String idAsiento) throws Exception{
        if(idAsiento == null || idAsiento.trim().isEmpty()){
            throw new Exception("Por favor, indique un numero de asiento valido.");
        }
        
        Asiento a = buscarAsientos(idAsiento);
        if(a == null){
            throw new Exception("El asiento |" + idAsiento + "| no existe en dicho avion");
        }
        if(a.isReservado()){
            throw new Exception("El asiento |" + idAsiento + "| ya se encuentra reservado");
        }
        
        a.reservarAsiento();
    }
    
    public void liberarAsientos(String idAsiento) throws Exception{
        if(idAsiento == null || idAsiento.trim().isEmpty()){
            throw new Exception("Por favor, indique un numero de asiento valido.");
        }
        
        Asiento a = buscarAsientos(idAsiento);
        if(a == null){
            throw new Exception("El asiento |" + idAsiento + "| no existe en dicho avion");
        }
        if(!a.isReservado()){
            throw new Exception("El asiento |" + idAsiento + "| ya se encuentra libre");
        }
        
        a.liberarAsiento();
    }
    
    // rediseñar estructura
    public int asientosDisponiblesClase(String tipoClase){
        if(tipoClase == null) return 0;
        int contadorAsientos = 0; 
        for(Asiento a : asientos){
            if(!a.isReservado() && a.getTipoClase().equalsIgnoreCase(tipoClase))
                contadorAsientos++;
        }
        return contadorAsientos;
    }
    
    public String toString(){
        return " Avion con matricula: |" + matriculaAvion + "|";
    }
}
