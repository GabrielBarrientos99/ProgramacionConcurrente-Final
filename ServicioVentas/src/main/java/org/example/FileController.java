package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileController {
    String FileRuta1;
    String FileRuta2;
    HashMap<Integer,Venta> DB1;
    HashMap<Integer,DetalleVenta> DB2;
    public FileController(String DB1,String DB2,HashMap<Integer,Venta> db1,HashMap<Integer,DetalleVenta> db2){
        this.FileRuta1=DB1;
        this.FileRuta2=DB2;
        this.DB1 = db1;
        this.DB2 = db2;
    }

    public boolean ExisteArchivo(String ruta){
        return new File(ruta).exists();
    }
    public void CreateArchivo(String ruta) throws IOException {
        File archivo = new File(ruta);
        if(archivo.createNewFile()){

        }else{
            System.out.println("El archivo ya existe.");
        }
    }

    public void Actualizar_DB1(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileRuta1))) {
            for (Venta venta : DB1.values()) {
                // Formatear la venta como una cadena y escribir en el archivo
                String ventaString = "[+] id_venta : "+venta.id_sales + " - [ " + venta.ruc + "," + venta.name + "," + venta.cost_total + " ] ";
                writer.write(ventaString);
                writer.newLine();
            }
            System.out.println("DB1 actualizado en el archivo: " + FileRuta1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Actualizar_DB2() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileRuta2))) {
            for (DetalleVenta detalleVenta : DB2.values()) {
                // Formatear el detalleVenta como una cadena y escribir en el archivo
                String detalleVentaString = "[+] id_venta: "+detalleVenta.id_sales + " - ";
                for (Producto producto : detalleVenta.productos.values()) {
                    detalleVentaString += " [ "+producto.id_prod + "," +
                            producto.name_prod + "," +
                            producto.amount + "," +
                            producto.cost + "," +
                            producto.total + " ] ";
                }
                writer.write(detalleVentaString);
                writer.newLine();
            }
            System.out.println("DB2 actualizado en el archivo: " + FileRuta2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
