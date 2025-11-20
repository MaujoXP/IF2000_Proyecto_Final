/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.domain;

/**
 * Representa una factura generada a partir de un tiquete.
 * La factura contiene información sobre el número de factura, 
 * el tiquete asociado, el subtotal, el impuesto aplicado y el total a pagar.
 * 
 * Esta clase permite calcular el total automáticamente en base al subtotal
 * y el porcentaje de impuesto definido.
 * 
 * @author mauri, alvin, ariana, wendoly
 */
public class Factura {

    /** Número único que identifica la factura. */
    private String numeroFactura;

    /** Tiquete asociado a la factura. */
    private Tiquete tiquete;

    /** Precio antes de impuestos, tomado del tiquete. */
    private double subtotal;

    /** Porcentaje de impuesto aplicado (por defecto 13%). */
    private double impuesto = 0.13;

    /** Total final después de aplicar el impuesto. */
    private double total;
    
    /**
     * Construye una factura basada en un tiquete.
     * El subtotal se toma del precio del tiquete y el total se calcula automáticamente.
     *
     * @param numeroFactura número identificador de la factura
     * @param tiquete tiquete al que pertenece la factura
     * @param impuesto porcentaje de impuesto a aplicar (por ejemplo, 0.13 para 13%)
     */
    public Factura(String numeroFactura, Tiquete tiquete, double impuesto) {
        this.numeroFactura = numeroFactura;
        this.tiquete = tiquete;
        this.impuesto = impuesto;
        this.subtotal = tiquete.getPrecio();
        this.total = calcularTotal();
    }
    
    /**
     * Calcula el total de la factura aplicando el impuesto al subtotal.
     *
     * @return total final con impuesto incluido
     */
    public double calcularTotal() {
        return subtotal + (subtotal * impuesto);
    }

    /** @return número de factura */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /** @return tiquete asociado */
    public Tiquete getTiquete() {
        return tiquete;
    }

    /** @return subtotal antes de impuestos */
    public double getSubtotal() {
        return subtotal;
    }

    /** @return porcentaje de impuesto aplicado */
    public double getImpuesto() {
        return impuesto;
    }

    /** @return total final a pagar */
    public double getTotal() {
        return total;
    }

    /**
     * Establece un nuevo número de factura.
     *
     * @param numeroFactura nuevo número de factura
     */
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    /**
     * Establece el tiquete asociado a la factura.
     *
     * @param tiquete nuevo tiquete
     */
    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }

    /**
     * Modifica el subtotal de la factura.
     *
     * @param subtotal nuevo subtotal
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Modifica el porcentaje de impuesto aplicado.
     *
     * @param impuesto nuevo valor de impuesto
     */
    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * Modifica el total de la factura.
     *
     * @param total nuevo total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Retorna una representación en texto de la factura.
     *
     * @return información detallada de la factura en formato legible
     */
    @Override
    public String toString() {
        return "Factura\nFactura N°: " + numeroFactura +
               "\nPasajero: " + tiquete.getPasajero().getNombre() +
               "\nVuelo: " + tiquete.getVuelo().getOrigenVuelo() + " → " + tiquete.getVuelo().getDestinoVuelo() +
               "\nClase: " + tiquete.getClase() +
               "\nSubtotal: ₡" + subtotal +
               "\nIVA (13%): ₡" + (subtotal * impuesto) +
               "\nTotal: ₡" + total;
    }
}
