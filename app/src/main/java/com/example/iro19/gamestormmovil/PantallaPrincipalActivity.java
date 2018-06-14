package com.example.iro19.gamestormmovil;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.iro19.gamestormmovil.negocio.Cuenta;
import com.example.iro19.gamestormmovil.negocio.Juego;
import com.example.iro19.gamestormmovil.task.JuegoTask;

import java.util.ArrayList;
import java.util.List;

public class PantallaPrincipalActivity extends AppCompatActivity {

    private ListView listaJuegos;
    private List<Juego> juegos;
    private JuegoAdaptador juegoAdaptador;
    private Cuenta cuenta;
    private ImageView imagenPrincipal;
    private PantallaPrincipalActivity pantallaPrincipalActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);
        cuenta = (Cuenta)getIntent().getSerializableExtra("cuenta");
        pantallaPrincipalActivity = this;

        listaJuegos = (ListView) findViewById(R.id.listaJuegos);
        imagenPrincipal = findViewById(R.id.imagenPrincipal);
        juegos = new ArrayList<Juego>();
        cargarListaJuegos();

        listaJuegos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(pantallaPrincipalActivity,DetallesJuegoActivity.class);
                intent.putExtra("juego", juegos.get(position));
                intent.putExtra("cuenta", cuenta);
                startActivity(intent);
            }
        });
    }

    private void cargarListaJuegos(){
        juegos.clear();
        new JuegoTask(juegos, listaJuegos, this).execute();
    }
}
