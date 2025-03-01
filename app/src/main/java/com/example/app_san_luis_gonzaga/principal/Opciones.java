package com.example.app_san_luis_gonzaga.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_san_luis_gonzaga.R;

public class Opciones extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistro = findViewById(R.id.btnRegistro);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Opciones.this, Login.class);
            startActivity(intent);
        });

        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(Opciones.this, Registro.class);
            startActivity(intent);
        });
    }
}
