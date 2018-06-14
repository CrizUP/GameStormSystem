package com.example.iro19.gamestormmovil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.sql.Date;
import com.example.iro19.gamestormmovil.negocio.Cuenta;
import com.example.iro19.gamestormmovil.negocio.Juego;
import com.example.iro19.gamestormmovil.negocio.Pedido;
import com.example.iro19.gamestormmovil.task.RegistrarPedidoTask;


public class ApartarJuegoActivity extends AppCompatActivity {
    private Cuenta cuenta;
    private Juego juego;
    private Pedido pedido;
    private TextView nombre;
    private TextView correo;
    private TextView telefono;
    private TextView consola;
    private ImageView imagenPortada;
    private TextView tituloJuego;
    private Button confirmarApartado;
    private Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartar);

        juego = (Juego) getIntent().getSerializableExtra("juego");
        cuenta = (Cuenta) getIntent().getSerializableExtra("cuenta");

        nombre = findViewById(R.id.lblNombre);
        correo = findViewById(R.id.lblCorreo);
        telefono = findViewById(R.id.lblTelefono);
        consola = findViewById(R.id.lblConsola);
        imagenPortada = findViewById(R.id.portadaJuego);
        tituloJuego = findViewById(R.id.tituloJuego);
        confirmarApartado = findViewById(R.id.btnConfirmarApartado);
        spinner = findViewById(R.id.spinner);

        imagenPortada.setImageBitmap(juego.getProxyBitmap().getBitmap());
        consola.setText(juego.getConsola());
        nombre.setText(cuenta.getPersona().getNombre() + " " + cuenta.getPersona().getApellidos());
        correo.setText(cuenta.getPersona().getCorreo());
        telefono.setText(cuenta.getPersona().getTelefono());
        tituloJuego.setText(juego.getNombreJuego());

        if (juego.getStock() > 0) {
            Integer[] datosSpinner = new Integer[juego.getStock()];
            for (int i = 0; i < juego.getStock(); i++) {
                datosSpinner[i] = i + 1;
            }

            ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, datosSpinner);
            spinner.setAdapter(adapter);
        } else {
            confirmarApartado.setEnabled(false);
        }
    }

    public void registrarPedido(View view) {
        pedido = new Pedido();
        pedido.setJuego(juego);
        pedido.setCuenta(cuenta);
        pedido.setCantidadJuegos((Integer) spinner.getSelectedItem());
        pedido.setFechaApartado(new Date(System.currentTimeMillis()));
        pedido.setFechaLimite(pedido.calcularFechaLimite(pedido.getFechaApartado()));
        pedido.setEstatus("Apartado");
        pedido.setPrecioTotal(juego.getPrecioJuego() * pedido.getCantidadJuegos());
        pedido.setFechaPago(null);
        pedido.setIdJuego(juego.getIdJuego());
        pedido.setUsuario(cuenta.getUsuario());

        RegistrarPedidoTask registrarTask = new RegistrarPedidoTask(pedido, juego, cuenta, this);
        registrarTask.execute();
    }
}
