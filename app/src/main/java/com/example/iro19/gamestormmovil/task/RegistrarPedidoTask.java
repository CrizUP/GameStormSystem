package com.example.iro19.gamestormmovil.task;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.iro19.gamestormmovil.ApartarJuegoActivity;
import com.example.iro19.gamestormmovil.DetallesJuegoActivity;
import com.example.iro19.gamestormmovil.PantallaPrincipalActivity;
import com.example.iro19.gamestormmovil.R;
import com.example.iro19.gamestormmovil.negocio.Cuenta;
import com.example.iro19.gamestormmovil.negocio.Juego;
import com.example.iro19.gamestormmovil.negocio.Pedido;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

public class RegistrarPedidoTask extends AsyncTask<Void, Void, Boolean> {
    private boolean resultado;
    private Pedido pedido;
    private Juego juego;
    private Cuenta cuenta;
    private ApartarJuegoActivity apartarJuegoActivity;

    public RegistrarPedidoTask(Pedido pedido, Juego juego, Cuenta cuenta, ApartarJuegoActivity apartarJuegoActivity) {
        this.pedido = pedido;
        this.juego = juego;
        this.cuenta = cuenta;
        this.apartarJuegoActivity = apartarJuegoActivity;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            //URL url = new URL("http://192.168.43.184:8080/GameStormServidor/webresources/modelo.pedido");
            URL url = new URL("http://192.168.100.14:8080/GameStormServidor/webresources/modelo.pedido");
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestMethod("POST");
            conexion.connect();

            JSONObject jsonPersona = new JSONObject();
            jsonPersona.accumulate("idPersona", cuenta.getPersona().getIdPersona());
            jsonPersona.accumulate("nombre", cuenta.getPersona().getNombre());
            jsonPersona.accumulate("apellidos", cuenta.getPersona().getApellidos());
            jsonPersona.accumulate("correo", cuenta.getPersona().getCorreo());
            jsonPersona.accumulate("telefono", cuenta.getPersona().getTelefono());
            jsonPersona.accumulate("sexo", cuenta.getPersona().getSexo());

            JSONObject jsonCuenta = new JSONObject();
            jsonCuenta.accumulate("usuario", cuenta.getUsuario());
            jsonCuenta.accumulate("contrasena", cuenta.getContrasena());
            jsonCuenta.accumulate("rol", cuenta.getRol());
            jsonCuenta.accumulate("idPersona", jsonPersona);

            JSONObject jsonJuego = new JSONObject();
            jsonJuego.accumulate("idJuego", juego.getIdJuego());
            jsonJuego.accumulate("nombreJuego", juego.getNombreJuego());
            jsonJuego.accumulate("descripcion", juego.getDescripcion());
            jsonJuego.accumulate("promedio", juego.getPromedio());
            jsonJuego.accumulate("stock", juego.getStock());
            jsonJuego.accumulate("precio", juego.getPrecioJuego());
            jsonJuego.accumulate("consola", juego.getConsola());
            jsonJuego.accumulate("imagen", juego.getNombreFoto());
            jsonJuego.accumulate("linkVideo", juego.getLinkVideo());
            jsonJuego.accumulate("genero", juego.getGenero());
            jsonJuego.accumulate("empresaDesarrolladora", juego.getDesarrolladores());

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("cantidadJuegos", pedido.getCantidadJuegos());
            jsonObject.accumulate("fechaApartado",  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(pedido.getFechaApartado()));
            jsonObject.accumulate("fechaLimite",  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(pedido.getFechaLimite()));
            jsonObject.accumulate("precioTotal", pedido.getPrecioTotal());
            jsonObject.accumulate("fechaPago", pedido.getFechaPago());
            jsonObject.accumulate("estatus", pedido.getEstatus());
            jsonObject.accumulate("idJuego", jsonJuego);
            jsonObject.accumulate("usuario", jsonCuenta);

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
        return resultado;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (resultado) {
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            AlertDialog.Builder alert = new AlertDialog.Builder(apartarJuegoActivity);
            alert.setTitle(R.string.pedidoExitoso);
            alert.setMessage("Fecha limite para recoger: " + formato.format(pedido.getFechaLimite()));
            alert.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(apartarJuegoActivity, "Fecha limite para recoger: " + formato.format(pedido.getFechaLimite()), Toast.LENGTH_LONG).show();
                    Intent intento = new Intent(apartarJuegoActivity,PantallaPrincipalActivity.class);
                    intento.putExtra("cuenta", cuenta);
                    apartarJuegoActivity.startActivity(intento);
                    apartarJuegoActivity.finish();

                }
            });
            Dialog dialogo = alert.create();
            dialogo.show();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(apartarJuegoActivity);
            alert.setTitle(R.string.error);
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
