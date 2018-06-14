package com.example.iro19.gamestormmovil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.iro19.gamestormmovil.negocio.Cuenta;
import com.example.iro19.gamestormmovil.negocio.Persona;
import com.example.iro19.gamestormmovil.task.RegistrarPersonaTask;

public class RegistroActivity extends AppCompatActivity {
    private Persona persona;
    private Cuenta cuenta;
    private EditText nombre;
    private EditText apellidos;
    private EditText correo;
    private EditText telefono;
    private EditText usuario;
    private EditText contrasena;
    private Spinner spinnerSexo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nombre = findViewById(R.id.lblNombre);
        apellidos = findViewById(R.id.txtApellidos);
        correo = findViewById(R.id.lblCorreo);
        telefono = findViewById(R.id.lblTelefono);
        spinnerSexo = findViewById(R.id.spinnerSexo);

        usuario = findViewById(R.id.txtUsuario);
        contrasena = findViewById(R.id.txtContrasena);
    }

    public void registrarUsuario(View view){
        persona = new Persona();
        cuenta = new Cuenta();
        persona.setNombre(nombre.getText().toString());
        persona.setApellidos(apellidos.getText().toString());
        persona.setCorreo(correo.getText().toString());
        persona.setTelefono(telefono.getText().toString());
        persona.setSexo((String) spinnerSexo.getSelectedItem());

        cuenta.setUsuario(usuario.getText().toString());
        cuenta.setContrasena(contrasena.getText().toString());
        cuenta.setRol("Cliente");

        if(! existenCamposVacios()) {
            RegistrarPersonaTask registrarTask = new RegistrarPersonaTask(persona, cuenta, this);
            registrarTask.execute();

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.registroExitoso);
            alert.setMessage(R.string.msjExitoso);
            alert.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RegistroActivity.this.finish();
                    //Toast.makeText(context, R.string.intenta, Toast.LENGTH_SHORT);
                }
            });
            Dialog dialogo = alert.create();
            dialogo.show();
        }else{
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
        }
    }

    public boolean existenCamposVacios(){
        boolean vacios = false;
        if(nombre.getText().toString().equals("") || apellidos.getText().toString().equals("") || correo.getText().toString().equals("")
                || telefono.getText().toString().equals("") ||usuario.getText().toString().equals("") || contrasena.getText().toString().equals("")){
            vacios= true;
        }
        return vacios;
    }
}
