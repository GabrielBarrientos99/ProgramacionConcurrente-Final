package PruebasCliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PruebaCliente {
    public static void main(String[] args){
        try {
            // Establecer conexión con el servidor en el puerto 50000
            Socket socket = new Socket("localhost", 50000);

            // Obtener el flujo de salida del socket
            OutputStream out = socket.getOutputStream();


            // JSON de prueba
            String json = "{ \"type\": \"UPDATE\", " +
                    "\"venta\": { \"id_sales\": 5, \"ruc\": \"1\", \"name\": \"1\", \"cost_total\": 1 }," +
                    " \"detalle_venta\": [" +
                    " { \"id_sales\": 5, \"id_prod\": 1, \"name_prod\": \"jabon\", \"amount\": 2, \"cost\": 10.00, \"total\": 20.00 }" +
                    ", { \"id_sales\": 5, \"id_prod\": 2, \"name_prod\": \"toalla\", \"amount\": 2, \"cost\": 10.00, \"total\": 20.00 }" +
                    ", { \"id_sales\": 5, \"id_prod\": 3, \"name_prod\": \"crema\", \"amount\": 2, \"cost\": 10.00, \"total\": 20.00 } ] }";


            // Convertir el JSON a bytes y enviar al servidor
            byte[] jsonBytes = json.getBytes();
            out.write(jsonBytes);


            // Cerrar el socket
            socket.close();

            System.out.println("JSON enviado al servidor.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

