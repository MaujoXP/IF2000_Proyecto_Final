/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

/**
 * Representa un tiquete asignado a un pasajero para un vuelo específico.
 * Cada tiquete posee un código único autogenerado, información del pasajero,
 * vuelo, clase del asiento y costo.
 * 
 * Los códigos de tiquete inician en 1000 y aumentan automáticamente.
 * 
 * @author mauri, alvin, ariana, wendoly
 */
public class Tiquete {

    /** Contador estático usado para generar códigos de tiquete únicos. */
    private static int contadorTiquetes = 1000;

    /** Código único del tiquete generado automáticamente. */
    private int codigoTiquete;

    /** Pasajero asociado a este tiquete. */
    private Pasajero pasajero;

    /** Vuelo correspondiente al tiquete. */
    private Vuelo vuelo;

    /** Clase del tiquete (Ej: "Económica", "Ejecutiva", etc.). */
    private String clase;

    /** Precio total del tiquete. */
    private double precio;

    /**
     * Crea un nuevo tiquete asignado a un pasajero y un vuelo.
     * El código del tiquete se autogenera usando el contador estático.
     *
     * @param pasajero Pasajero al que se asigna el tiquete
     * @param vuelo Vuelo correspondiente
     * @param clase Clase o categoría del asiento
     * @param precio Precio total del tiquete
     */
    public Tiquete(Pasajero pasajero, Vuelo vuelo, String clase, double precio) {
        this.codigoTiquete = contadorTiquetes++;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.clase = clase;
        this.precio = precio;
    }

    /** @return valor actual del contador de tiquetes. */
    public static int getContadorTiquetes() {
        return contadorTiquetes;
    }

    /** @return código único del tiquete. */
    public int getCodigoTiquete() {
        return codigoTiquete;
    }

    /** @return pasajero al que pertenece el tiquete. */
    public Pasajero getPasajero() {
        return pasajero;
    }

    /** @return vuelo asignado al tiquete. */
    public Vuelo getVuelo() {
        return vuelo;
    }

    /** @return clase del asiento del tiquete. */
    public String getClase() {
        return clase;
    }

    /** @return precio del tiquete. */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece manualmente el contador estático de tiquetes.
     * (Debe usarse solo para reinicios o pruebas).
     * 
     * @param contadorTiquetes nuevo valor del contador
     */
    public static void setContadorTiquetes(int contadorTiquetes) {
        Tiquete.contadorTiquetes = contadorTiquetes;
    }

    /**
     * Modifica el código del tiquete.
     * (No recomendado salvo para ajustes administrativos).
     *
     * @param codigoTiquete nuevo código
     */
    public void setCodigoTiquete(int codigoTiquete) {
        this.codigoTiquete = codigoTiquete;
    }

    /**
     * Modifica el pasajero asignado al tiquete.
     *
     * @param pasajero nuevo pasajero
     */
    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    /**
     * Modifica el vuelo asignado al tiquete.
     *
     * @param vuelo nuevo vuelo
     */
    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    /**
     * Modifica la clase del tiquete.
     *
     * @param clase nueva clase
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * Modifica el precio del tiquete.
     *
     * @param precio nuevo precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Representación en texto de los datos del tiquete.
     *
     * @return información formateada del tiquete
     */
    @Override
    public String toString() {
        return "Tiquete\nCodigoTiquete: " + codigoTiquete +
                "\nPasajero: " + pasajero +
                "\nVuelo: " + vuelo + 
                "\nClase: " + clase +
                "\nPrecio: ₡" + precio;
    }
}