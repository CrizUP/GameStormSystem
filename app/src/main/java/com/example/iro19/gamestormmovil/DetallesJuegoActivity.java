package com.example.iro19.gamestormmovil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.iro19.gamestormmovil.negocio.Cuenta;
import com.example.iro19.gamestormmovil.negocio.Juego;

public class DetallesJuegoActivity extends AppCompatActivity {
    private Juego juego;
    private TextView tituloJuego;
    private TextView lblDescripcionJuego;
    private TextView lblTrailer;
    private TextView lblGenero;
    private TextView lblDesarrollador;
    private TextView lblConsola;
    private Button btnApartar;
    private RatingBar ratingBar;
    private ImageView imagenPortada;
    private Context context;
    private Cuenta cuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_detalles_juego);
        juego = (Juego)getIntent().getSerializableExtra("juego");
        cuenta = (Cuenta)getIntent().getSerializableExtra("cuenta");
        btnApartar = findViewById(R.id.btnApartar);
        tituloJuego =findViewById(R.id.tituloJuego);
        lblDescripcionJuego = findViewById(R.id.lblDescripcion);
        lblTrailer = findViewById(R.id.lblTrailer);
        lblGenero = findViewById(R.id.lblGenero);
        lblDesarrollador = findViewById(R.id.lblDesarrollador);
        lblConsola =  findViewById(R.id.lblConsola);
        ratingBar = findViewById(R.id.ratingBar);
        imagenPortada = findViewById(R.id.portadaJuego);

        tituloJuego.setText(juego.getNombreJuego());
        lblDescripcionJuego.setText(juego.getDescripcion());
        lblTrailer.setText(juego.getLinkVideo());
        lblGenero.setText(juego.getGenero());
        lblDesarrollador.setText(juego.getDesarrolladores());
        lblConsola.setText(juego.getConsola());
        imagenPortada.setImageBitmap(juego.getProxyBitmap().getBitmap());
        ratingBar.setRating(juego.getPromedio());

        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        btnApartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(context, ApartarJuegoActivity.class);
                intento.putExtra("juego", juego);
                intento.putExtra("cuenta", cuenta);
                startActivity(intento);
            }
        });
    }

}
