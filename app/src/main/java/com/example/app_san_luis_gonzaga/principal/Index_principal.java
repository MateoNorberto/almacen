package com.example.app_san_luis_gonzaga.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_san_luis_gonzaga.R;

public class Index_principal extends AppCompatActivity {

    private Button btnRegistrarProducto, btnMostrarProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_principal);

        btnRegistrarProducto = findViewById(R.id.btnRegistrarProducto);
        btnMostrarProductos = findViewById(R.id.btnMostrarProductos);

        btnRegistrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad para registrar un producto
                Intent intent = new Intent(Index_principal.this, registrar_producto.class);
                startActivity(intent);
            }
        });

        btnMostrarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la actividad para mostrar productos
                Intent intent = new Intent(Index_principal.this, mostrar_producto.class); // Cambié el nombre a "MostrarProducto" (con mayúscula)
                startActivity(intent);
            }
        });
    }
}
