package com.example.iro19.gamestormmovil.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.iro19.gamestormmovil.negocio.Juego;
import com.example.iro19.gamestormmovil.negocio.ProxyBitmap;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DescargarFotoTask extends AsyncTask<Void, Void, Boolean> {

    private Juego juego;
    private ImageView imagen;

    public DescargarFotoTask(Juego juego, ImageView imagen) {
        this.juego = juego;
        this.imagen = imagen;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try{
            //URL url = new URL("http://192.168.43.184:8080/GameStormServidor/imagenesJuegos/" + juego.getIdJuego() + ".jpg");
            URL url = new URL("http://192.168.100.14:8080/GameStormServidor/imagenesJuegos/" + juego.getIdJuego() + ".jpg");
            Bitmap imagenJuego = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            ProxyBitmap pr = new ProxyBitmap(imagenJuego);
            juego.setProxyBitmap(pr);
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            imagen.setImageBitmap(juego.getProxyBitmap().getBitmap());
        }
    }
}
