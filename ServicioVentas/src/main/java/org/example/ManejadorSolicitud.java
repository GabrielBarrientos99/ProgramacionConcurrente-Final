package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class ManejadorSolicitud implements Runnable{

    Socket socketCliente;
    InputStream in;
    OutputStream out;

    HashMap<Integer,Venta> DB1;
    HashMap<Integer,DetalleVenta> DB2;

    FileController f ;

    public ManejadorSolicitud(Socket socketCliente, HashMap<Integer,Venta> DB1,HashMap<Integer,DetalleVenta> DB2,FileController f) throws IOException {
        this.socketCliente=socketCliente;
        this.in = socketCliente.getInputStream();
        this.out = socketCliente.getOutputStream();
        this.DB1 = DB1;
        this.DB2 = DB2;
        this.f = f;
        System.out.println("Se creo el hilo");
    }

    @Override
    public void run(){
        System.out.println("Ingresando al run");
        String json = InputToJson();
        System.out.println(json);

        // Creamos el executor
        Executor executor = new Executor(DB1,DB2);

        //Procesamos la solicitud
        JSONObject jsonObj = new JSONObject(json);
        String type = jsonObj.getString("type");
        System.out.println("type : "+ type);

        switch (type) {
            case "CREATE":
                executor.CREATE(jsonObj);
                f.Actualizar_DB1();
                f.Actualizar_DB2();
                break;
            case "READ":
                System.out.println("Hola entre al READ");
                String mensaje_json = executor.READ(jsonObj);
                System.out.println(mensaje_json);
                PrintWriter printWriter = new PrintWriter(out, true);
                printWriter.println(mensaje_json);
                break;
            case "READALL":
                String mensaje_json2 = executor.READALL(jsonObj);
                PrintWriter printWriter2 = new PrintWriter(out, true);
                printWriter2.println(mensaje_json2);

                break;
            case "UPDATE":
                executor.UPDATE(jsonObj);
                f.Actualizar_DB1();
                f.Actualizar_DB2();
                break;
            case "DELETE":
                executor.DELETE(jsonObj);
                f.Actualizar_DB1();
                f.Actualizar_DB2();
                break;
            default:
                System.out.println("Tipo de solicitud no válido.");
        }


    }

    private String InputToJson() {
        Scanner scanner=new Scanner(in);
        StringBuilder json = new StringBuilder();
        if (scanner.hasNextLine()){
            String linea = scanner.nextLine();
            System.out.println("Leyendo lineas :"+linea);
            json.append(linea);
        }
        return json.toString();
    }


}
