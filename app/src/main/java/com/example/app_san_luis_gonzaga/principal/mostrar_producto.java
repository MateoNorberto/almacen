package com.example.app_san_luis_gonzaga.principal;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_san_luis_gonzaga.R;
import com.example.app_san_luis_gonzaga.basedate.DatabaseHelper;

import java.util.ArrayList;

public class mostrar_producto extends AppCompatActivity {

    private ListView listViewProductos;
    private ArrayList<producto> listaProductos;  // Cambié mostrar_producto a Producto
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos);  // Asegúrate de tener el layout correcto

        listViewProductos = findViewById(R.id.listViewProductos);
        dbHelper = new DatabaseHelper(this);

        // Obtener la lista de productos de la base de datos
        listaProductos = dbHelper.obtenerProductos();

        // Crear un adaptador para el ListView
        ArrayAdapter<producto> adaptador = new ArrayAdapter<producto>(
                this,
                android.R.layout.simple_list_item_2,  // Usando el layout predeterminado de 2 líneas
                listaProductos
        ) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                producto producto = listaProductos.get(position);

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                text1.setText(producto.getNombre());
                text2.setText("Precio: $" + producto.getPrecio());

                return view;
            }
        };

        // Establecer el adaptador al ListView
        listViewProductos.setAdapter(adaptador);

        // Acción al hacer clic en un ítem
        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                producto productoSeleccionado = listaProductos.get(position);
                Toast.makeText(mostrar_producto.this, "Seleccionaste: " + productoSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
