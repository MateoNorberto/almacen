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

    private EditText etNombre, etTipoProducto, etNumeroSerie;
    private Button btnRegistrar;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro); // Asegúrate de que el nombre del archivo XML es correcto

        dbHelper = new DatabaseHelper(this);

        // Asignación de los EditText y Button usando los IDs correctos del XML
        etNombre = findViewById(R.id.etNombre); // Nombre del producto
        etTipoProducto = findViewById(R.id.tvTipoProducto); // Tipo de producto
        etNumeroSerie = findViewById(R.id.etNumeroSerie); // Número de serie
        btnRegistrar = findViewById(R.id.btnRegistrar); // Botón de registrar

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString().trim(); // Nombre del producto
                String tipoProducto = etTipoProducto.getText().toString().trim(); // Tipo de producto
                String numeroSerie = etNumeroSerie.getText().toString().trim(); // Número de serie

                if (nombre.isEmpty() || tipoProducto.isEmpty() || numeroSerie.isEmpty()) {
                    // Si alguno de los campos está vacío, mostrar mensaje de error
                    Toast.makeText(Registro.this, "Por favor, ingresa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Registrar producto usando el método del DatabaseHelper
                    String fechaLlegada = "2025-03-07";  // Aquí puedes poner una fecha dinámica si lo deseas
                    boolean isProductAdded = dbHelper.insertarProducto(nombre, numeroSerie, tipoProducto, fechaLlegada);
                    if (isProductAdded) {
                        Toast.makeText(Registro.this, "Producto registrado exitosamente", Toast.LENGTH_SHORT).show();

                        // Redirigir a la actividad de Opciones (o la que desees)
                        // Asegúrate de que 'Opciones.class' esté bien definida
                        startActivity(new Intent(Registro.this, Opciones.class));
                        finish(); // Finaliza la actividad actual
                    } else {
                        Toast.makeText(Registro.this, "Error al registrar producto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
