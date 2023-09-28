package org.cibertec.edu.pe.cl1.model;

import lombok.Data;

@Data
public class CarritoItem {
    private Producto producto;
    private int cantidad;
    private double descuento;
    private double envio;
    
    
}
