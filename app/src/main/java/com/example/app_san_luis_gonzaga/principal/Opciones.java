package com.example.app_san_luis_gonzaga.principal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app_san_luis_gonzaga.R;

public class Opciones extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones); // Asegúrate de que el nombre del archivo XML es correcto

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistro = findViewById(R.id.btnRegistro);

        // Redirigir al Login
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Opciones.this, Login.class); // Asegúrate de tener la actividad Login
            startActivity(intent);
        });

        // Redirigir al Registro
        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(Opciones.this, Registro_producto.class); // Asegúrate de tener la actividad Registro
            startActivity(intent);
        });
    }
}
