package com.example.iro19.gamestormmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.iro19.gamestormmovil.negocio.Juego;
import com.example.iro19.gamestormmovil.task.JuegoTask;

import java.util.ArrayList;
import java.util.List;

public class PantallaPrincipalActivity extends AppCompatActivity {

    private ListView listaJuegos;
    private List<Juego> juegos;
    private JuegoAdaptador juegoAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        listaJuegos = (ListView) findViewById(R.id.listaJuegos);
        juegos = new ArrayList<Juego>();
        cargarListaJuegos();

        listaJuegos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void cargarListaJuegos(){
        juegos.clear();
        new JuegoTask(juegos, listaJuegos).execute();
    }
}
