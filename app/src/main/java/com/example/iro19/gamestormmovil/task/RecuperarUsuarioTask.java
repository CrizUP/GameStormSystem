package com.example.iro19.gamestormmovil.task;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.iro19.gamestormmovil.MainActivity;
import com.example.iro19.gamestormmovil.PantallaPrincipalActivity;
import com.example.iro19.gamestormmovil.R;
import com.example.iro19.gamestormmovil.negocio.Cuenta;
import com.example.iro19.gamestormmovil.negocio.Juego;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecuperarUsuarioTask extends AsyncTask<Void, Void, Boolean> {
    private boolean resultado;
    private Cuenta cuenta;
    private String idCuenta;

    private MainActivity mainActivity;

    public RecuperarUsuarioTask(MainActivity mainActivity, String idCuenta) {
        this.mainActivity = mainActivity;
        this.idCuenta = idCuenta;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            //URL url = new URL("http://192.168.43.184:8080/GameStormServidor/webresources/modelo.cuenta/" + idCuenta);
            URL url = new URL("http://192.168.100.14:8080/GameStormServidor/webresources/modelo.cuenta/" + idCuenta);
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

            JSONObject jsonCuenta = new JSONObject(cadena);

            if(cadena != null){
                cuenta = new Cuenta(jsonCuenta);
                resultado = true;
            }else{
                resultado = false;
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resultado;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(cuenta != null) {
            Intent intento = new Intent(mainActivity, PantallaPrincipalActivity.class);
            intento.putExtra("cuenta", cuenta);
            mainActivity.startActivity(intento);
            mainActivity.finish();
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(mainActivity);
            alert.setTitle(R.string.titulAlert);
            alert.setMessage(R.string.noConexion);
            alert.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(context, R.string.intenta, Toast.LENGTH_SHORT);
                }
            });
            Dialog dialogo = alert.create();
            dialogo.show();
        }
    }
}
