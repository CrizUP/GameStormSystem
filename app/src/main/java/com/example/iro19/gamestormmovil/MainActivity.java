package com.example.iro19.gamestormmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pantallaPrincipal(View vista){
        Intent intento = new Intent(this, PantallaPrincipalActivity.class);
        startActivity(intento);
    }
}
