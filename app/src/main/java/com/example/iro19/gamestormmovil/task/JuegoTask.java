package com.example.iro19.gamestormmovil.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.iro19.gamestormmovil.JuegoAdaptador;
import com.example.iro19.gamestormmovil.PantallaPrincipalActivity;
import com.example.iro19.gamestormmovil.R;
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
    private boolean resultado;
    private List<Juego> juegos;
    private ListView listaJuegos;
    private Context context;

    public JuegoTask(List<Juego> juegos, ListView listaJuegos, Context context) {
        this.juegos = juegos;
        this.listaJuegos = listaJuegos;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            //URL url = new URL("http://192.168.43.184:8080/GameStormServidor/webresources/modelo.juego");
            URL url = new URL("http://192.168.100.14:8080/GameStormServidor/webresources/modelo.juego");
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

            if(cadena != null){
                resultado = true;
            }else{
                resultado = false;
            }

        }catch(Exception ex){

        }
        return resultado;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        JuegoAdaptador lista = new JuegoAdaptador(context, R.layout.activity_juego ,juegos);
        listaJuegos.setAdapter(lista);
        listaJuegos.invalidateViews();
    }
}
