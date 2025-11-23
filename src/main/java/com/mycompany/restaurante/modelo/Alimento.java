package com.mycompany.restaurante.modelo;

public abstract class Alimento {
    private int id;
    private String nombre;
    private double precio;

    public Alimento() {}

    public Alimento(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    @Override
    public String toString() { return nombre + " ($" + precio + ")"; }
}