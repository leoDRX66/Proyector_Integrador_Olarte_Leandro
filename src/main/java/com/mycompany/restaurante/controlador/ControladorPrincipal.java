package com.mycompany.restaurante.controlador;

import com.mycompany.restaurante.modelo.Persona;
import com.mycompany.restaurante.modelo.SistemaRestaurante;
import com.mycompany.restaurante.vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorPrincipal implements ActionListener {

    private VentanaPrincipal menu;
    private SistemaRestaurante sistema;

    public ControladorPrincipal(VentanaPrincipal menuRecibido) {
        this.menu = menuRecibido;
        this.sistema = new SistemaRestaurante();
        
        this.menu.btnGestionAlimentos.addActionListener(this);
        this.menu.btnVerChefs.addActionListener(this);
        this.menu.btnVerMozos.addActionListener(this);
        this.menu.btnNuevoPedido.addActionListener(this);
        this.menu.btnSalir.addActionListener(this);

        this.menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.btnGestionAlimentos) {
            abrirGestionAlimentos();
        } 
        else if (e.getSource() == menu.btnVerChefs) {
            mostrarListaDePersonal("CHEF", "Lista de Chefs");
        } 
        else if (e.getSource() == menu.btnVerMozos) {
            mostrarListaDePersonal("MESERO", "Lista de Mozos");
        } 
        else if (e.getSource() == menu.btnNuevoPedido) {
            abrirGenerarPedido();
        } 
        else if (e.getSource() == menu.btnSalir) {
            System.exit(0);
        }
    }

    private void abrirGestionAlimentos() {
        VistaAlimentos ventanaAlimentos = new VistaAlimentos();
        new ControladorAlimentos(ventanaAlimentos, sistema); 
    }

    private void abrirGenerarPedido() {
        VistaPedido ventanaPedido = new VistaPedido();
        new ControladorPedido(ventanaPedido, sistema);
    }

    private void mostrarListaDePersonal(String tipoPersona, String tituloVentana) {
        VistaPersonal ventanaLista = new VistaPersonal(tituloVentana);
        List<Persona> listaPersonas = sistema.obtenerPersonalPorTipo(tipoPersona);

        if (listaPersonas != null) {
            for (Persona p : listaPersonas) {    
                Object[] filaDeTabla = {p.getCedula(), p.getNombre(), p.getTelefono()};
                ventanaLista.modelo.addRow(filaDeTabla);
            }
        }
        ventanaLista.setVisible(true);
    }
}