package com.example.app_san_luis_gonzaga.basedate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.app_san_luis_gonzaga.principal.producto;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "AppDB";
    private static final int DATABASE_VERSION = 1;

    // Tabla de usuarios
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";  // Columna de correo

    // Tabla de productos
    private static final String TABLE_PRODUCTS = "productos";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "nombre";
    private static final String COLUMN_SERIAL = "numero_serie";
    private static final String COLUMN_TYPE = "tipo";
    private static final String COLUMN_DATE = "fecha_llegada";
    private static final String COLUMN_PRODUCT_PRICE = "precio";  // Precio del producto

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Crear las tablas en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_EMAIL + " TEXT UNIQUE)";  // Crear columna de correo

        // Crear tabla de productos
        String createProductsTable = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCT_NAME + " TEXT, " +
                COLUMN_SERIAL + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_PRODUCT_PRICE + " REAL)"; // Precio añadido

        // Ejecutar las sentencias SQL para crear las tablas
        db.execSQL(createUsersTable);
        db.execSQL(createProductsTable);
    }

    // Actualizar la base de datos (si hay cambios de versión)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tablas existentes y crear nuevas
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    // ----------------------------------- Métodos de Gestión de Usuarios -----------------------------------

    // Insertar un usuario
    public boolean insertarUsuario(String nombre, String correo, String contrasena) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, nombre);  // Nombre del usuario
        contentValues.put(COLUMN_EMAIL, correo);    // Correo del usuario
        contentValues.put(COLUMN_PASSWORD, contrasena);  // Contraseña del usuario

        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1; // Si result es -1, hubo un error
    }

    // Verificar si el usuario existe con nombre de usuario y contraseña
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " =? AND " + COLUMN_PASSWORD + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    // Verificar si el usuario existe con el nombre de usuario
    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    // Verificar si el nombre de usuario o correo ya están registrados
    public boolean isUsernameOrEmailTaken(String username, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " =? OR " + COLUMN_EMAIL + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{username, email});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    // Validar las credenciales del usuario
    public boolean validarUsuario(String correo, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_EMAIL + " =? AND " + COLUMN_PASSWORD + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{correo, contrasena});

        boolean valid = cursor != null && cursor.moveToFirst();
        cursor.close();
        return valid;  // Retorna verdadero si las credenciales son correctas
    }

    // ----------------------------------- Métodos de Gestión de Productos -----------------------------------

    // Insertar un producto
    public boolean insertarProducto(String nombre, String numeroSerie, String tipo, String fechaLlegada) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_NAME, nombre);
        contentValues.put(COLUMN_SERIAL, numeroSerie);
        contentValues.put(COLUMN_TYPE, tipo);
        contentValues.put(COLUMN_DATE, fechaLlegada);  // Fecha de llegada del producto

        long result = db.insert(TABLE_PRODUCTS, null, contentValues);
        return result != -1;  // Si result es -1, hubo un error al insertar
    }

    // Obtener todos los productos
    public ArrayList<producto> obtenerProductos() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<producto> productos = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME));
                String numeroSerie = cursor.getString(cursor.getColumnIndex(COLUMN_SERIAL));
                String tipo = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                String fechaLlegada = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                double precio = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRODUCT_PRICE));

                productos.add(new producto(nombre, precio, numeroSerie, tipo, fechaLlegada));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productos;
    }

    // Verificar si un producto con el número de serie existe
    public boolean isProductExists(String numeroSerie) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_SERIAL + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{numeroSerie});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}
