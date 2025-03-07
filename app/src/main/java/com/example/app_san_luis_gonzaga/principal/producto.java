package com.example.app_san_luis_gonzaga.principal;

public class producto {
    private String nombre;
    private double precio;
    private String numeroSerie;
    private String tipo;
    private String fechaLlegada;

    // Constructor que acepta todos los parámetros
    public producto(String nombre, double precio, String numeroSerie, String tipo, String fechaLlegada) {
        this.nombre = nombre;
        this.precio = precio;
        this.numeroSerie = numeroSerie;
        this.tipo = tipo;
        this.fechaLlegada = fechaLlegada;
    }

    // Getters y setters para los atributos
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
}
