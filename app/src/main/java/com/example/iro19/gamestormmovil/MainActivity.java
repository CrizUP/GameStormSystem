package com.example.iro19.gamestormmovil;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.iro19.gamestormmovil.task.IniciarSesionTask;

public class MainActivity extends AppCompatActivity {
    private EditText campoUsuario;
    private EditText campoContrasena;
    private Button botonIniciar;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        campoUsuario = (EditText) findViewById(R.id.txtUsuario);
        campoContrasena = (EditText) findViewById(R.id.txtContrasena);
        botonIniciar = (Button) findViewById(R.id.btnIniciar);

    }

    public void pantallaPrincipal(View vista){
        if(campoUsuario.getText().toString().equals("") || campoContrasena.getText().toString().equals("")){
            android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(this);
            alert.setTitle(R.string.titulAlert);
            alert.setMessage(R.string.mensajeAlert2);
            alert.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(context, R.string.intenta, Toast.LENGTH_SHORT);
                }
            });
            Dialog dialogo = alert.create();
            dialogo.show();
        }else {
            IniciarSesionTask loginTask = new IniciarSesionTask(campoUsuario.getText().toString(), campoContrasena.getText().toString(), this);
            loginTask.execute();
        }
    }

    public void pantallaRegistrar(View view){
        Intent intento = new Intent(context, RegistroActivity.class);
        startActivity(intento);
        //MainActivity.this.finish();
    }
}
