/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

/**
 * Representa a un pasajero dentro del sistema de reservaciones.
 * Cada pasajero cuenta con información básica como nombre, cédula,
 * correo electrónico y número telefónico.
 * 
 * Esta clase permite crear y modificar los datos personales del pasajero.
 * 
 * @author mauri, alvin, ariana, wendoly
 */
public class Pasajero {

    /** Nombre completo del pasajero. */
    private String nombre;

    /** Número de cédula del pasajero. */
    private String cedula;

    /** Correo electrónico del pasajero. */
    private String correo;

    /** Número telefónico del pasajero. */
    private String telefono;  
    
    /**
     * Construye un objeto Pasajero con todos sus datos personales.
     *
     * @param nombre nombre completo del pasajero
     * @param cedula número de identificación del pasajero
     * @param correo correo electrónico del pasajero
     * @param telefono número telefónico del pasajero
     */
    public Pasajero(String nombre, String cedula, String correo, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
    }

    /** @return nombre del pasajero */
    public String getNombre() {
        return nombre;
    }

    /** @return cédula del pasajero */
    public String getCedula() {
        return cedula;
    }

    /** @return correo electrónico del pasajero */
    public String getCorreo() {
        return correo;
    }

    /** @return número telefónico del pasajero */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece un nuevo nombre para el pasajero.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece una nueva cédula para el pasajero.
     *
     * @param cedula nueva cédula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Establece un nuevo correo electrónico para el pasajero.
     *
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Establece un nuevo número telefónico para el pasajero.
     *
     * @param telefono nuevo teléfono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Retorna una representación en texto de la información del pasajero.
     *
     * @return datos del pasajero en formato legible
     */
    @Override
    public String toString() {
        return "Pasajero\nNombre: " + nombre + "\n" +
               "\nCédula: " + cedula + "\n" +
               "\nCorreo: " + correo + "\n" +
               "\nTeléfono: " + telefono;
    }
}