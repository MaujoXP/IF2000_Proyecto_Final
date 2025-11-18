/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

/**
 *
 * @author mauri, alvin
 */
public class Tiquete {
    private static int contadorTiquetes = 1000;
    private int codigoTiquete;
    private Pasajero pasajero;
    private Vuelo vuelo;
    private String clase;
    private double precio;

    public Tiquete(Pasajero pasajero, Vuelo vuelo, String clase, double precio) {
        this.codigoTiquete = contadorTiquetes++;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.clase = clase;
        this.precio = precio;
    }

    public static int getContadorTiquetes() {
        return contadorTiquetes;
    }

    public int getCodigoTiquete() {
        return codigoTiquete;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public String getClase() {
        return clase;
    }

    public double getPrecio() {
        return precio;
    }

    public static void setContadorTiquetes(int contadorTiquetes) {
        Tiquete.contadorTiquetes = contadorTiquetes;
    }

    public void setCodigoTiquete(int codigoTiquete) {
        this.codigoTiquete = codigoTiquete;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Tiquete\nCodigoTiquete: " + codigoTiquete +
                "\nPasajero: " + pasajero +
                "\nVuelo: " + vuelo + 
                "\nClase: " + clase +
                "\nPrecio: ₡" + precio;
    }
}
