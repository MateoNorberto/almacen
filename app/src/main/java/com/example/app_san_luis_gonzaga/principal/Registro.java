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
import com.example.app_san_luis_gonzaga.principal.Login;


public class Registro extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        dbHelper = new DatabaseHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Registro.this, "Por favor, ingresa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUserAdded = dbHelper.addUser(username, password);
                    if (isUserAdded) {
                        Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registro.this, Login.class));
                        finish();
                    } else {
                        Toast.makeText(Registro.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
