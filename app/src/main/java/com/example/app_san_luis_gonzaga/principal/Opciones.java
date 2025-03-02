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

    public static class MainActivity extends AppCompatActivity {

        private Button btnRegister, btnLogin, btnLogout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btnRegister = findViewById(R.id.btnRegister);
            btnLogin = findViewById(R.id.btnLogin);
            btnLogout = findViewById(R.id.btnLogout);

            // Redirigir al registro
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Llamamos a la actividad de registro
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
            });

            // Redirigir al inicio de sesión
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Llamamos a la actividad de inicio de sesión
                    Intent intent = new Intent(MainActivity.this, Bievenido.class);
                    startActivity(intent);
                }
            });

            // Cerrar sesión (solo vuelve a la pantalla principal o a la pantalla de bienvenida)
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Termina la sesión y vuelve a la pantalla de inicio
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish(); // Finaliza esta actividad para que no se quede en la pila
                }
            });
        }
    }
}
