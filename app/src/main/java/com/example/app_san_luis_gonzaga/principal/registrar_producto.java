package com.example.app_san_luis_gonzaga.principal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_san_luis_gonzaga.R;
import com.example.app_san_luis_gonzaga.basedate.DatabaseHelper;

public class RegistrarProducto extends AppCompatActivity {

    private EditText etNombre, etNumeroSerie, etTipo, etFechaLlegada;
    private Button btnGuardarProducto;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_registrar); // Asegúrate de que el nombre del archivo XML es correcto

        dbHelper = new DatabaseHelper(this);

        etNombre = findViewById(R.id.etNombre);
        etNumeroSerie = findViewById(R.id.etNumeroSerie);
        etTipo = findViewById(R.id.etTipo);
        etFechaLlegada = findViewById(R.id.etFechaLlegada);
        btnGuardarProducto = findViewById(R.id.btnGuardarProducto);

        btnGuardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarProducto();
            }
        });
    }

    private void registrarProducto() {
        String nombre = etNombre.getText().toString().trim();
        String numeroSerie = etNumeroSerie.getText().toString().trim();
        String tipo = etTipo.getText().toString().trim();
        String fechaLlegada = etFechaLlegada.getText().toString().trim();

        if (nombre.isEmpty() || numeroSerie.isEmpty() || tipo.isEmpty() || fechaLlegada.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            if (nombre.isEmpty()) etNombre.requestFocus();
            else if (numeroSerie.isEmpty()) etNumeroSerie.requestFocus();
            else if (tipo.isEmpty()) etTipo.requestFocus();
            else etFechaLlegada.requestFocus();
            return;
        }

        boolean isInserted = dbHelper.insertarProducto(nombre, numeroSerie, tipo, fechaLlegada);
        if (isInserted) {
            Toast.makeText(this, "Producto registrado con éxito", Toast.LENGTH_SHORT).show();
            finish(); // Cierra la actividad y vuelve a Index
        } else {
            Toast.makeText(this, "Error al registrar el producto", Toast.LENGTH_SHORT).show();
        }
    }
}

