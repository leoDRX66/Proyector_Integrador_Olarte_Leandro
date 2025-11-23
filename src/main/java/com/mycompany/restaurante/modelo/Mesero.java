package com.mycompany.restaurante.modelo;

import com.mycompany.restaurante.modelo.Empleado;
import java.sql.Time;
import java.util.Date;


public class Mesero extends Empleado{
    private double salario;

    public Mesero(Date fechaVinculacion, Time horaIngreso, Time horaSalida, String nombre, String cedula, String telefono) {
        super(fechaVinculacion, horaIngreso, horaSalida, nombre, cedula, telefono);
    }

    public Mesero(double salario, Date fechaVinculacion, Time horaIngreso, Time horaSalida, String nombre, String cedula, String telefono) {
        super(fechaVinculacion, horaIngreso, horaSalida, nombre, cedula, telefono);
        this.salario = salario;
    }

    public Mesero(double salario, Date fechaVinculacion, Time horaIngreso, Time horaSalida) {
        super(fechaVinculacion, horaIngreso, horaSalida);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    //
    public void tomarPedido(String[] p){
        
    }
    
    public void cancelarPedido(String c){
        
    }
    
    public void modificarPedido(String m){
        
    }
    
    public void entregarPedido(String e){
        
    }
    
}
