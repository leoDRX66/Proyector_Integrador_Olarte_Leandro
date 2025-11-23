package com.mycompany.restaurante.controlador;

import com.mycompany.restaurante.dao.*;
import com.mycompany.restaurante.modelo.Persona;
import com.mycompany.restaurante.vista.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPrincipal implements ActionListener {

    private VentanaPrincipal menu;
    private PersonaDAO personaDAO;
    private AlimentoDAO alimentoDAO;
    private PedidoDAO pedidoDAO;

    public ControladorPrincipal(VentanaPrincipal menuRecibido) {
        this.menu = menuRecibido;
        this.personaDAO = new PersonaDAO();
        this.alimentoDAO = new AlimentoDAO();
        this.pedidoDAO = new PedidoDAO(); 
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
        new ControladorAlimentos(ventanaAlimentos, alimentoDAO);
    }

    private void abrirGenerarPedido() {
        VistaPedido ventanaPedido = new VistaPedido();
        new ControladorPedido(ventanaPedido, personaDAO, alimentoDAO, pedidoDAO);
    }

    private void mostrarListaDePersonal(String tipoPersona, String tituloVentana) {
        VistaPersonal ventanaLista = new VistaPersonal(tituloVentana);
        
        List<Persona> listaPersonas = personaDAO.listarPorTipo(tipoPersona);

        if (listaPersonas != null) {
            for (Persona p : listaPersonas) {    
                String cedula = p.getCedula();
                String nombre = p.getNombre();
                String telefono = p.getTelefono();
                Object[] filaDeTabla = {cedula, nombre, telefono};
                ventanaLista.modelo.addRow(filaDeTabla);
            }
        }

        ventanaLista.setVisible(true);
    }
}