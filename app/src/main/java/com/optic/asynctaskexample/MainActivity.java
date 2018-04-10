package com.optic.asynctaskexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mTextViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTextViewData = (TextView) findViewById(R.id.textViewData);
    }

    /*
     * METODO QUE PERMITE VALIDAR EL ESTADO DE LA RED
     */
    public boolean isOnline() {
        // OBTENIENDO EL SERVICIO DE LA CONECTIVIDAD
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // DE ConnectivityManager OBTENGO SI ESTA O NO ACTIVA LA RED
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        // SI HAY CONEXION
        if(networkInfo != null) {
            return true;
        }
        else {
            return false;
        }
    }

    /*
     * ONCLICK CORRER TAREA ASINCRONA
     */
    public void OnClickRunAsyncTask(View view) {
        if(isOnline()) {
            Toast.makeText(this, "Todo OK", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_SHORT).show();
        }
    }

    public class MyTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for(int i = 1; i < 50; i++) {
                // PASANDO INFORMACION DESDE UN HILO DE SEGUNDO PLANO AL HILO PRINCIPAL
                publishProgress(String.valueOf(i));
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
