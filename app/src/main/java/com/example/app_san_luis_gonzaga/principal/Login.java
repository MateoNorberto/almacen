package com.example.app_san_luis_gonzaga.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_san_luis_gonzaga.R;
import com.example.app_san_luis_gonzaga.basedate.DatabaseHelper;

public class Login extends AppCompatActivity {

    private EditText etLoginUsername, etLoginPassword;
    private Button btnLogin, btnGoToRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        dbHelper = new DatabaseHelper(this);

        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoToRegister = findViewById(R.id.btnGoToRegister);

        // Botón de inicio de sesión
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Botón de registro que también inicia sesión automáticamente
        btnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAndLoginUser();
            }
        });
    }

    private void loginUser() {
        String username = etLoginUsername.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            boolean isValidUser = dbHelper.checkUser(username, password);
            if (isValidUser) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                goToHome();
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void registerAndLoginUser() {
        String username = etLoginUsername.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingresa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            boolean isRegistered = dbHelper.insertUser(username, password);
            if (isRegistered) {
                Toast.makeText(this, "Registro exitoso. Iniciando sesión...", Toast.LENGTH_SHORT).show();
                goToHome();  // Redirigir al usuario después del registro
            } else {
                Toast.makeText(this, "Error al registrar. Intenta con otro usuario.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void goToHome() {
        Intent intent = new Intent(Login.this,Login.class);
        startActivity(intent);
        finish();  // Cierra la pantalla de login
    }
}
