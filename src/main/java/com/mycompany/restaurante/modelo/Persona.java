package com.mycompany.restaurante.modelo;


public class Persona {
    private String nombre;
    private String cedula;
    private String telefono;
    private String usuario=null;
    private String contraseña=null;

    public Persona(String nombre, String cedula, String telefono) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    //
    public void restablecerContraseña(){
    }
    
    public void iniciarSesion(String[] s){
        
    }
    @Override
    public String toString() {
        return this.nombre; // Esto hace que en el Combo Box se vea el nombre y no "com.mycompany..."
    }
}
