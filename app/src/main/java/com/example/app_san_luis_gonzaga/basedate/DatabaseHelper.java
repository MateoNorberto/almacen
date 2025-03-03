package com.example.app_san_luis_gonzaga.basedate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Definición de la base de datos
    private static final String DATABASE_NAME = "UserDB";
    private static final int DATABASE_VERSION = 1;

    // Tabla usuarios
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Tabla productos
    private static final String TABLE_PRODUCTS = "productos";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "nombre";
    private static final String COLUMN_SERIAL = "numero_serie";
    private static final String COLUMN_TYPE = "tipo";
    private static final String COLUMN_DATE = "fecha_llegada";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT UNIQUE, "
                + COLUMN_PASSWORD + " TEXT)";

        String createProductsTable = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PRODUCT_NAME + " TEXT, "
                + COLUMN_SERIAL + " TEXT, "
                + COLUMN_TYPE + " TEXT, "
                + COLUMN_DATE + " TEXT)";

        db.execSQL(createUsersTable);
        db.execSQL(createProductsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    // Métodos para la gestión de usuarios
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " =? AND " + COLUMN_PASSWORD + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " =?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    // Métodos para la gestión de productos
    public boolean insertarProducto(String nombre, String numeroSerie, String tipo, String fechaLlegada) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRODUCT_NAME, nombre);
        contentValues.put(COLUMN_SERIAL, numeroSerie);
        contentValues.put(COLUMN_TYPE, tipo);
        contentValues.put(COLUMN_DATE, fechaLlegada);

        long result = db.insert(TABLE_PRODUCTS, null, contentValues);
        return result != -1;
    }

    public Cursor obtenerProductos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);
    }
}
