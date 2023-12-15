package PruebasCliente;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class PruebaCliente3 {
    public static void main(String[] args){
        try {
            // Establecer conexión con el servidor en el puerto 50000
            Socket socket = new Socket("localhost", 50000);

            // Obtener el flujo de salida del socket
            OutputStream out = socket.getOutputStream();

            String json = "{ \"type\": \"READALL\", " +
                    "\"venta\": { \"id_sales\": 7 } }";

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

