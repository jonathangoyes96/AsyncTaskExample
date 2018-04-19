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

import com.optic.asynctaskexample.Models.Post;
import com.optic.asynctaskexample.Parser.Json;
import com.optic.asynctaskexample.URL.HttpManager;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mTextViewData;

    private List<Post> postList = new ArrayList<>();

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
     * ONCLICK EJECUTAR TAREA ASINCRONA
     */
    public void OnClickRunAsyncTask(View view) {
        if(isOnline()) {
            //MyTask myTask = new MyTask();
            //myTask.execute("https://jsonplaceholder.typicode.com/posts");

            TaskCountry taskCountry = new TaskCountry();
            taskCountry.execute("http://services.groupkt.com/country/get/all");

        }
        else {
            Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_SHORT).show();
        }
    }

    /*
     * METODO QUE PERMITE PROCESAR LOS DATOS
     */
    public void processData() {
        // mTextViewData.append(data + "\n");
        Toast.makeText(this, String.valueOf(postList.size()), Toast.LENGTH_SHORT).show();

        for(Post post  : postList) {
            mTextViewData.append(post.toString());
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
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            processData();
        }

        // String s CONTIENE TODOS LOS DATOS PROVENIENTES DE INTERNET
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                postList = Json.getDataJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
            mProgressBar.setVisibility(View.GONE);
        }
    }

    public class TaskCountry extends AsyncTask<String , String, String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
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
