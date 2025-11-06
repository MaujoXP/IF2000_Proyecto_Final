/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

/**
 *
 * @author mauri
 */
public class Factura {
    private String numeroFactura;
    private Tiquete tiquete;
    private double subtotal;
    private double impuesto = 0.13;
    private double total;
    
    public Factura(String numeroFactura, Tiquete tiquete, double impuesto) {
        this.numeroFactura = numeroFactura;
        this.tiquete = tiquete;
        this.impuesto = impuesto;
        this.subtotal = tiquete.getPrecio();
        this.total = calcularTotal();
    }
    
    public double calcularTotal() {
        return subtotal + (subtotal * impuesto);
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura\nFactura N°: " + numeroFactura +
               "\nPasajero: " + tiquete.getPasajero().getNombre();
               "\nVuelo: " + tiquete.getVuelo().getOrigen() + " → " + tiquete.getVuelo().getDestino() +
               "\nClase: " + tiquete.getClase() +
               "\nSubtotal: ₡" + subtotal +
               "\nIVA (13%): ₡" + (subtotal * impuesto) +
               "\nTotal: ₡" + total;
    }
    
    

}
