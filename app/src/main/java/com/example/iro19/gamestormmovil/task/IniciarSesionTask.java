package com.example.iro19.gamestormmovil.task;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.iro19.gamestormmovil.MainActivity;
import com.example.iro19.gamestormmovil.PantallaPrincipalActivity;
import com.example.iro19.gamestormmovil.R;
import com.example.iro19.gamestormmovil.negocio.Cuenta;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IniciarSesionTask extends AsyncTask<Void, Void, Boolean> {
    private String usuario;
    private String contrasena;
    private boolean resultado;
    private MainActivity mainActivity;

    public IniciarSesionTask(String usuario, String contrasena, MainActivity mainActivity) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.mainActivity = mainActivity;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String cadena;
        try {
            contrasena = cifrarContrasena(contrasena);

            //URL url = new URL("http://192.168.43.184:8080/GameStormServidor/webresources/modelo.cuenta/login/" + usuario + "/" + contrasena);
            URL url = new URL("http://192.168.100.14:8080/GameStormServidor/webresources/modelo.cuenta/login/" + usuario + "/" + contrasena);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.connect();

            InputStream inputStream;
            if (conexion.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = conexion.getInputStream();
            } else {
                inputStream = conexion.getErrorStream();
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            cadena = bufferedReader.readLine();

            if(cadena.equals("true")){
                resultado = true;
            }else{
                resultado = false;
            }
        } catch (Exception ex) {
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
            ex.printStackTrace();
        }
        return resultado;
    }

    public String cifrarContrasena(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(contrasena.getBytes(Charset.forName("UTF-8")));
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(resultado) {
            RecuperarUsuarioTask usuarioTask = new RecuperarUsuarioTask(mainActivity, usuario);
            usuarioTask.execute();
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(mainActivity);
            alert.setTitle(R.string.titulAlert);
            alert.setMessage(R.string.mensajeAlert);
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
