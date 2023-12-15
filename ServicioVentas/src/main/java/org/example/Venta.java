package org.example;

import org.json.JSONObject;

public class Venta {
    int id_sales;
    String ruc;
    String name;
    double cost_total;

    public Venta(int id_sales,String ruc,String name,double cost_total){
        this.id_sales=id_sales;
        this.ruc= ruc;
        this.name = name;
        this.cost_total=cost_total;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonVenta = new JSONObject();
        jsonVenta.put("id_sales", id_sales);
        jsonVenta.put("ruc", ruc);
        jsonVenta.put("name", name);
        jsonVenta.put("cost_total", cost_total);

        return jsonVenta;
    }
}
