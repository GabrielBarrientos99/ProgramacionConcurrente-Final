package org.example;

import java.util.HashMap;

public class DetalleVenta {

    int id_sales;
    HashMap<Integer,Producto> productos;

    public DetalleVenta(int id_sales){
        this.id_sales = id_sales;
        productos = new HashMap<>();
    }
}
