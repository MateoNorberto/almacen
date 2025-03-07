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

public class Registro_producto extends AppCompatActivity {

    private EditText etNombre, etCorreo, etContrasena, etConfirmarContrasena;
    private Button btnRegistrar;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario); // Asegúrate de tener el nombre correcto del archivo XML

        // Inicializar campos y base de datos
        dbHelper = new DatabaseHelper(this);
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        etConfirmarContrasena = findViewById(R.id.etConfirmarContrasena);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        // Acción del botón de registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        // Obtener los valores de los campos
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();
        String confirmarContrasena = etConfirmarContrasena.getText().toString().trim();

        // Validar campos
        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(correo) || TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(confirmarContrasena)) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si el nombre de usuario o correo ya existen
        if (dbHelper.isUsernameOrEmailTaken(nombre, correo)) {
            Toast.makeText(this, "El nombre de usuario o correo electrónico ya está registrado", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insertar el usuario en la base de datos
        boolean isInserted = dbHelper.insertarUsuario(nombre, correo, contrasena);
        if (isInserted) {
            Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();

            // Redirigir al usuario a la actividad Registro (donde registrarás productos)
            Intent intent = new Intent(Registro_producto.this, Registro.class);
            startActivity(intent); // Inicia la actividad Registro
            finish(); // Cierra la actividad actual
        } else {
            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }
}
