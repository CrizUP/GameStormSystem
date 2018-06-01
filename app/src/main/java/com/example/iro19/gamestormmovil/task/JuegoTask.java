package com.example.iro19.gamestormmovil.task;

import android.os.AsyncTask;
import android.widget.ListView;

import com.example.iro19.gamestormmovil.negocio.Juego;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JuegoTask extends AsyncTask<Void, Void, Boolean> {

    private List<Juego> juegos;
    private ListView listaJuegos;

    public JuegoTask(List<Juego> juegos, ListView listaJuegos) {
        this.juegos = juegos;
        this.listaJuegos = listaJuegos;
    }

    public JuegoTask(){

    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            URL url = new URL("http://10.40.7.28:8080/GameStorm/webresources/modelo.juego");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.connect();

            InputStream inputStream;
            if(conexion.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST){
                inputStream = conexion.getInputStream();
            }else{
                inputStream = conexion.getErrorStream();
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String cadena = bufferedReader.readLine();

            JSONArray jsonRespuesta = new JSONArray(cadena);

            for(int i = 0; i < jsonRespuesta.length(); i++){
                JSONObject jsonJuego = jsonRespuesta.getJSONObject(i);
                juegos.add(new Juego(jsonJuego));
            }

        }catch(Exception ex){

        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            listaJuegos.invalidateViews();
        }else{

        }
    }
}
