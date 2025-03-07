package com.example.app_san_luis_gonzaga.principal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_san_luis_gonzaga.R;
import com.example.app_san_luis_gonzaga.basedate.DatabaseHelper;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Asegúrate de que el nombre del archivo XML sea correcto

        // Inicializar campos y base de datos
        dbHelper = new DatabaseHelper(this);
        etUsername = findViewById(R.id.etLoginUsername); // Cambié aquí el ID a 'etLoginUsername'
        etPassword = findViewById(R.id.etLoginPassword); // Cambié aquí el ID a 'etLoginPassword'
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnGoToRegister);

        // Acción del botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Acción del botón de registro (Redirigir a la pantalla de registro)
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la pantalla de registro
                startActivity(new Intent(Login.this, Registro_producto.class));
            }
        });
    }

    private void loginUser() {
        // Obtener los valores de los campos
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Comprobar si el usuario existe
        if (dbHelper.checkUser(username, password)) {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
            // Aquí puedes redirigir a la actividad principal o donde desees
            startActivity(new Intent(Login.this, Index_principal.class)); // Redirigir a la actividad Opciones
            finish(); // Cierra la actividad de login
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
