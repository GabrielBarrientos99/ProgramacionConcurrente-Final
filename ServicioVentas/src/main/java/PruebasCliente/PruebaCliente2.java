package PruebasCliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PruebaCliente2 {
    public static void main(String[] args){
        try {
            // Establecer conexión con el servidor en el puerto 50000
            Socket socket = new Socket("localhost", 50000);

            // Obtener el flujo de salida del socket
            OutputStream out = socket.getOutputStream();

            String json = "{ \"type\": \"READ\", " +
                    "\"venta\": { \"id_sales\": 7 } }";

            // Convertir el JSON a bytes y enviar al servidor
            PrintWriter printWriter = new PrintWriter(out, true);
            printWriter.println(json);
            printWriter.flush();


            InputStream in = socket.getInputStream();
            Scanner sc = new Scanner(in);
            System.out.println("hola:");
            if(sc.hasNextLine()){

                String line = sc.nextLine();
                System.out.println(line);
            }

            // Cerrar el socket
            socket.close();

            System.out.println("JSON enviado al servidor.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

