package com.example.iro19.gamestormmovil.task;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.example.iro19.gamestormmovil.R;
import com.example.iro19.gamestormmovil.RegistroActivity;
import com.example.iro19.gamestormmovil.negocio.Cuenta;
import com.example.iro19.gamestormmovil.negocio.Persona;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrarPersonaTask extends AsyncTask<Void, Void, Boolean> {
    private boolean resultado;
    private Persona persona;
    private Cuenta cuenta;
    private RegistroActivity registro;
    private int idPersona;

    public RegistrarPersonaTask(Persona persona, Cuenta cuenta, RegistroActivity registro) {
        this.persona = persona;
        this.cuenta = cuenta;
        this.registro = registro;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            //URL url = new URL("http://192.168.43.184:8080/GameStormServidor/webresources/modelo.persona");
            URL url = new URL("http://192.168.100.14:8080/GameStormServidor/webresources/modelo.persona");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestMethod("POST");
            conexion.connect();

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("nombre", persona.getNombre());
            jsonObject.accumulate("apellidos", persona.getApellidos());
            jsonObject.accumulate("correo", persona.getCorreo());
            jsonObject.accumulate("telefono", persona.getTelefono());
            jsonObject.accumulate("sexo", persona.getSexo());


            OutputStream outputStream = conexion.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(String.valueOf(jsonObject));
            bufferedWriter.flush();

            InputStream inputStream;
            if (conexion.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = conexion.getInputStream();
                resultado = true;
            } else {
                inputStream = conexion.getErrorStream();
                resultado = false;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String cadena = bufferedReader.readLine();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        registrarCuenta();
        return resultado;
    }

    public JSONObject obtenerID() {
        JSONObject persona2 = new JSONObject();
        try {
            //URL url = new URL("http://192.168.43.184:8080/GameStormServidor/webresources/modelo.persona/ultimo");
            URL url = new URL("http://192.168.100.14:8080/GameStormServidor/webresources/modelo.persona/ultimo");
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
            String cadena = bufferedReader.readLine();

            persona2= new JSONObject(cadena);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return persona2;
    }

    public void registrarCuenta(){
        try {
            //URL url = new URL("http://192.168.43.184:8080/GameStormServidor/webresources/modelo.cuenta");
            URL url = new URL("http://192.168.100.14:8080/GameStormServidor/webresources/modelo.cuenta");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestMethod("POST");
            conexion.connect();
            JSONObject jsonObject = new JSONObject();
            JSONObject personaJson = obtenerID();
            jsonObject = new JSONObject();
            jsonObject.accumulate("usuario", cuenta.getUsuario());
            jsonObject.accumulate("contrasena", cifrarContrasena(cuenta.getContrasena()));
            jsonObject.accumulate("rol", cuenta.getRol());
            jsonObject.accumulate("idPersona", personaJson);

            OutputStream outputStream = conexion.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(String.valueOf(jsonObject));
            bufferedWriter.flush();

            InputStream inputStream;
            if (conexion.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = conexion.getInputStream();
            } else {
                inputStream = conexion.getErrorStream();
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String cadena = bufferedReader.readLine();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void registroExitoso() {
        if (resultado) {
            AlertDialog.Builder alert = new AlertDialog.Builder(registro);
            alert.setTitle(R.string.registroExitoso);
            alert.setMessage(R.string.msjExitoso);
            alert.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(context, R.string.intenta, Toast.LENGTH_SHORT);
                }
            });
            Dialog dialogo = alert.create();
            dialogo.show();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(registro);
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

        registro.finish();
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
}
