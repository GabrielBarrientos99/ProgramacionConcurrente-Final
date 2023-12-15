package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServidorVentas {
    int puerto;
    ServerSocket serverSocket;

    // Base de Datos
    HashMap<Integer, Venta > Database_venta;
    HashMap<Integer, DetalleVenta > Database_Detalles_venta;

    FileController fc ;

    public ServidorVentas(int puerto) throws IOException {
        this.puerto=puerto;
        this.serverSocket = new ServerSocket(puerto);
        this.Database_venta = new HashMap<>();
        this.Database_Detalles_venta=new HashMap<>();
        fc = new FileController("DB1.txt","DB2.txt",Database_venta,Database_Detalles_venta);
        fc.CreateArchivo(fc.FileRuta1);
        fc.CreateArchivo(fc.FileRuta2);
    }
    public void listening() throws IOException {
        while(true){
            System.out.println("Escuchando en el puerto ["+puerto+"] . . .");
            Socket socketCliente = serverSocket.accept();
            System.out.println("Cliente conectado desde " + socketCliente.getInetAddress());
            ManejadorSolicitud hilo = new ManejadorSolicitud(socketCliente,Database_venta,Database_Detalles_venta,fc);
            new Thread(hilo).start();

        }
    }

}
