package org.example;

import org.json.JSONObject;

public class Producto {
    int id_sales;
    int id_prod;
    String name_prod;
    int amount;
    double cost;
    double total;

    public Producto(int id_sales){
        this.id_sales = id_sales;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonProducto = new JSONObject();
        jsonProducto.put("id_prod", id_prod);
        jsonProducto.put("name_prod", name_prod);
        jsonProducto.put("amount", amount);
        jsonProducto.put("cost", cost);
        jsonProducto.put("total", total);

        return jsonProducto;
    }
}
