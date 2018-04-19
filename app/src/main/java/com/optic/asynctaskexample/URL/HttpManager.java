package com.optic.asynctaskexample.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 12/04/2018.
 */

public class HttpManager {

    public static String getData(String url) throws IOException {
        // CLASE PARA MANEJAR ARCHIVOS
        BufferedReader bufferedReader;

        // CLASE PARA MANEJAR LAS URL DE INTERNET
        URL urlData = new URL(url);

        // CLASE PARA ABRIR LA CONEXION A INTERNET
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlData.openConnection();

        // LOS DATOS QUE VIENEN EN BINARIO LOS PASA A ALGO QUE PUEDE SER INTERPRETADO POR EL DISPOSTIVO MOVIL
        StringBuilder stringBuilder = new StringBuilder();

        // LEER DATOS DE INTERNET
        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

        String line;
        while ( (line = bufferedReader.readLine()) != null ) {
            stringBuilder.append(line + "\n");
        }

        return stringBuilder.toString();

    }

}
