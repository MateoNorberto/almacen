package com.example.app_san_luis_gonzaga.principal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.app_san_luis_gonzaga.R;
import com.example.app_san_luis_gonzaga.principal.Bievenido;
import com.example.app_san_luis_gonzaga.principal.Opciones;

import androidx.appcompat.app.AppCompatActivity;

public class Bievenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bievenido);

        // Usamos Handler para redirigir después de 3 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Redirige a la actividad de login después de 3 segundos
                Intent intent = new Intent(Bievenido.this, Opciones.class);
                startActivity(intent);
                finish(); // Finaliza esta actividad para que no se quede en la pila
            }
        }, 1000); // 3000 milisegundos = 3 segundos
    }
}
