package com.mycompany.restaurante.modelo;

import com.mycompany.restaurante.modelo.Mesero;
import com.mycompany.restaurante.modelo.Cliente;
import com.mycompany.restaurante.modelo.Alimento;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private Cliente cliente;
    private Mesero mesero;
    private Date fechaPedido;
    private Time horaPedido;
    private double precioTotalPedido;
    private boolean estado;
    private ArrayList<Alimento> alimentos = new ArrayList<>();

    // Constructor más simple y lógico
    public Pedido(Cliente cliente, Mesero mesero) {
        this.cliente = cliente;
        this.mesero = mesero;
        this.fechaPedido = new Date(); // Fecha actual
        this.horaPedido = new Time(System.currentTimeMillis()); // Hora actual
        this.estado = true; // Pedido abierto
        this.precioTotalPedido = 0.0;
    }
    
    // Método para añadir alimentos y actualizar el precio
    public void addAlimento(Alimento alimento) {
        this.alimentos.add(alimento);
        this.precioTotalPedido += alimento.getPrecio();
    }

    // Getters y Setters necesarios...
    public Cliente getCliente() { return cliente; }
    public Mesero getMesero() { return mesero; }
    public Date getFechaPedido() { return fechaPedido; }
    public double getPrecioTotalPedido() { return precioTotalPedido; }
    public ArrayList<Alimento> getAlimentos() { return alimentos; }
}