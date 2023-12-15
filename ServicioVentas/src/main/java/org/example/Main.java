package org.example;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        ServidorVentas serverVentas = new ServidorVentas(50000);
        serverVentas.listening();
    }
}