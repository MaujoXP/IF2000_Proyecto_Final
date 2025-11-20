/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

/**
 * Representa un avión con una matrícula única y un conjunto predefinido
 * de asientos. El avión contiene 4 asientos: dos de clase Ejecutiva y dos 
 * de clase Económica. Permite buscar, reservar y liberar asientos, así como 
 * consultar cuántos asientos disponibles hay según la clase.
 * 
 * Métodos principales:
 * - buscarAsientos: busca un asiento por su ID.
 * - reservarAsientos: reserva un asiento si existe y no está reservado.
 * - liberarAsientos: libera un asiento previamente reservado.
 * - asientosDisponiblesClase: cuenta asientos disponibles por tipo de clase.
 * 
 * 
 * @author mauri, alvin, ariana, wendoly
 */
public class Avion {
    
    private String matriculaAvion;
    private Asiento[] asientos;
    
    /**
     * Constructor del avión. Crea la matrícula e inicializa los 4 asientos:
     * 1A - Ejecutiva
     * 1B - Ejecutiva
     * 2A - Económica
     * 2B - Económica
     * 
     * @param matriculaAvion Matrícula única del avión.
     */
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
    
    /**
     * Busca un asiento por su ID (por ejemplo "1A").
     * El método normaliza el texto (trim y mayúsculas) antes de buscar.
     * 
     * @param idAsiento ID del asiento a buscar.
     * @return Asiento encontrado o null si no existe.
     */
    public Asiento buscarAsientos(String idAsiento){
        if(idAsiento == null){
            return null; 
        }
        String claveAsiento = idAsiento.trim().toUpperCase();
        if(claveAsiento.isEmpty()){
            return null;
        }
        
        for(int i = 0; i < asientos.length; i++){
            Asiento actual = asientos[i];
            if(actual == null){
                continue;
            }
            
            String asientoActual = (actual.getIdAsiento() == null) ? "" : 
                    actual.getIdAsiento().trim().toUpperCase();
            
            if(asientoActual.equals(claveAsiento)){
                return actual;
            }
        }
        return null;
    }
    
    /**
     * Reserva un asiento si existe y no está reservado.
     * 
     * @param idAsiento ID del asiento a reservar.
     * @throws Exception si el asiento no existe o ya está reservado.
     */
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
    
    /**
     * Libera un asiento reservado.
     * 
     * @param idAsiento ID del asiento a liberar.
     * @throws Exception si el asiento no existe o ya está libre.
     */
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
    
    /**
     * Cuenta cuántos asientos disponibles existen según un tipo de clase.
     * 
     * @param tipoClase "Ejecutiva" o "Economica"
     * @return cantidad de asientos disponibles de esa clase.
     */
    public int asientosDisponiblesClase(String tipoClase){
        if(tipoClase == null) return 0;
        int contadorAsientos = 0; 
        for(Asiento a : asientos){
            if(!a.isReservado() && a.getTipoClase().equalsIgnoreCase(tipoClase))
                contadorAsientos++;
        }
        return contadorAsientos;
    }
    
    @Override
    public String toString(){
        return " Avion con matricula: |" + matriculaAvion + "|";
    }
}
