package com.example.app_san_luis_gonzaga.principal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_san_luis_gonzaga.R;
import com.example.app_san_luis_gonzaga.basedate.DatabaseHelper;

public class Index extends AppCompatActivity {

    private ListView listViewProductos;
    private Button btnRegistrarProducto;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_registro); // Asegúrate de que este layout existe.

        dbHelper = new DatabaseHelper(this);
        listViewProductos = findViewById(R.id.listViewProductos);
        btnRegistrarProducto = findViewById(R.id.btnRegistrarProducto);

        // Cargar los productos en el ListView
        cargarProductos();

        // Botón para registrar un nuevo producto
        btnRegistrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Index.this, RegistrarProducto.class));
            }
        });
    }

    private void cargarProductos() {
        Cursor cursor = dbHelper.obtenerProductos();

        // Verifica si hay datos
        if (cursor != null && cursor.getCount() > 0) {
            String[] fromColumns = {"nombre", "numero_serie", "tipo", "fecha_llegada"};
            int[] toViews = {R.id.tvNombreProducto, R.id.tvNumeroSerie, R.id.tvTipoProducto, R.id.tvFechaLlegada};

            // Adaptador para el ListView
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(

