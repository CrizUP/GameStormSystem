package com.example.iro19.gamestormmovil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iro19.gamestormmovil.negocio.Juego;

import java.util.List;
import java.util.zip.Inflater;

public class JuegoAdaptador extends ArrayAdapter{

    private List<Juego> juegos;
    private int layout;
    private Context context;

    public JuegoAdaptador(@NonNull Context context, int resource, @NonNull List<Juego> juegos) {
        super(context, resource, juegos);
        this.context = context;
        this.layout = resource;
        this.juegos = juegos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(position >= 0 && position < juegos.size()){
            Juego juego = juegos.get(position);
            listItem = LayoutInflater.from(context).inflate(R.layout.activity_juego, parent, false);

            TextView tituloJuego = listItem.findViewById(R.id.lblNombreJuego);
            TextView precioJuego = listItem.findViewById(R.id.lblPrecioJuego);
            ImageView fotoPortada = listItem.findViewById(R.id.portadaJuego);

            tituloJuego.setText(juego.getNombreJuego());
            precioJuego.setText((int) juego.getPrecioJuego());

            if(juego.getNombreFoto() != null){
                fotoPortada.setImageBitmap(juego.getNombreFoto());
            }else{

            }
        }
        return listItem;
    }
}
