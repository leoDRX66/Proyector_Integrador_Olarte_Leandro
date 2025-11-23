package com.mycompany.restaurante.modelo;

import java.sql.Time;
import java.util.Date;


public class Empleado extends Persona{
    private Date fechaVinculacion;
    private Time horaIngreso;
    private Time horaSalida;

    public Empleado(Date fechaVinculacion, Time horaIngreso, Time horaSalida, String nombre, String cedula, String telefono) {
        super(nombre, cedula, telefono);
        this.fechaVinculacion = fechaVinculacion;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
    }

    public Empleado(Date fechaVinculacion, Time horaIngreso, Time horaSalida) {
        this.fechaVinculacion = fechaVinculacion;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
    }

    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(Date fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public Time getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Time horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    
    //
    public void registrarEntrada(Time te){
        
    }
    
    public void registrarSalida(Time ts){
        
    }
}
