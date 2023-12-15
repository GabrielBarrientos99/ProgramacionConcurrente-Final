package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class Executor implements Procesos{
    HashMap<Integer,Venta> DB1;
    HashMap<Integer,DetalleVenta> DB2;

    public Executor(HashMap DB1,HashMap DB2){
        this.DB1=DB1;
        this.DB2=DB2;
    }

    @Override
    public void CREATE(JSONObject a){
        int id_venta = a.getJSONObject("venta").getInt("id_sales");
        String ruc = a.getJSONObject("venta").getString("ruc");
        String name = a.getJSONObject("venta").getString("name");
        double cost_total = a.getJSONObject("venta").getDouble("cost_total");

        Venta v = new Venta(id_venta,ruc,name,cost_total);
        DB1.put(id_venta,v);

        DetalleVenta dv = new DetalleVenta(id_venta);
        JSONArray detalleVenta = a.getJSONArray("detalle_venta");
        for (int i = 0; i < detalleVenta.length(); i++) {
            JSONObject detalle = detalleVenta.getJSONObject(i);
            Producto p = new Producto(id_venta);
            p.id_prod = detalle.getInt("id_prod");
            p.name_prod = detalle.getString("name_prod");
            p.amount = detalle.getInt("amount");
            p.cost = detalle.getDouble("cost");
            p.total = detalle.getDouble("total");
            dv.productos.put(p.id_prod,p);
        }
        DB2.put(id_venta,dv);
    }
    @Override
    public String READ(JSONObject a) {

        int id_venta = a.getJSONObject("venta").getInt("id_sales");
        // Recuperar la venta y sus detalles desde los HashMap
        Venta v = DB1.get(id_venta);
        DetalleVenta dv = DB2.get(id_venta);

        if (v != null && dv != null) {
            // Crear un nuevo JSONObject para enviar la respuesta
            JSONObject response = new JSONObject();
            response.put("type", "READ");
            response.put("venta", v.toJSONObject());  //

            JSONArray detalleVentaArray = new JSONArray();
            for (Producto p : dv.productos.values()) {
                detalleVentaArray.put(p.toJSONObject());  //
            }
            response.put("detalle_venta", detalleVentaArray);

            // Enviar la respuesta al cliente o hacer algo con ella
            System.out.println(response.toString());
            return response.toString();

        } else {
            System.out.println("No se encontró la venta con ID: " + id_venta);
            return "";
        }

    }

    @Override
    public String READALL(JSONObject a) {
        JSONArray ventasArray = new JSONArray();

        for (Venta v : DB1.values()) {
            JSONObject ventaJson = v.toJSONObject();
            DetalleVenta dv = DB2.get(v.id_sales);

            if (dv != null) {
                JSONArray detalleVentaArray = new JSONArray();
                for (Producto p : dv.productos.values()) {
                    detalleVentaArray.put(p.toJSONObject());
                }

                ventaJson.put("detalle_venta", detalleVentaArray);
            }

            ventasArray.put(ventaJson);
        }

        JSONObject response = new JSONObject();
        response.put("type", "READALL");
        response.put("ventas", ventasArray);
        System.out.println(response.toString());
        return response.toString();
    }
    @Override
    public void UPDATE(JSONObject a){
        CREATE(a);
    }
    @Override
    public void DELETE(JSONObject a) {
        int id_venta = a.getJSONObject("venta").getInt("id_sales");
        // Eliminar la venta y sus detalles de los HashMap
        Venta v = DB1.remove(id_venta);
        DetalleVenta dv = DB2.remove(id_venta);

        if (v != null && dv != null) {
            // Imprimir mensaje o hacer algo con la eliminación
            System.out.println("Venta con ID " + id_venta + " eliminada.");
        } else {
            System.out.println("No se encontró la venta con ID: " + id_venta);
        }
    }
}
