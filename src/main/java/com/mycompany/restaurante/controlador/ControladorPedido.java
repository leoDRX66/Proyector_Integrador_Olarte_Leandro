package com.mycompany.restaurante.controlador;

import com.mycompany.restaurante.modelo.*;
import com.mycompany.restaurante.vista.VistaPedido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorPedido implements ActionListener {
    private VistaPedido vista;
    private SistemaRestaurante sistema;

    public ControladorPedido(VistaPedido vista, SistemaRestaurante sistema) {
        this.vista = vista;
        this.sistema = sistema;
        
        vista.btnAgregar.addActionListener(this);
        vista.btnEntregar.addActionListener(this);
        
        cargarCombos();
        actualizarTabla();
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            guardarPedido();
        } else if (e.getSource() == vista.btnEntregar) {
            entregarPedido();
        }
    }

    private void guardarPedido() {
        try {
            Persona chef = (Persona) vista.cbChef.getSelectedItem();
            Persona mesero = (Persona) vista.cbMesero.getSelectedItem();
            Alimento plato = (Alimento) vista.cbPlato.getSelectedItem();
            Alimento bebida = (Alimento) vista.cbBebida.getSelectedItem();
            String txtCliente = vista.tfCliente.getText();

            if (chef == null || mesero == null || plato == null || txtCliente.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Faltan datos obligatorios.");
                return;
            }

            int idCliente = Integer.parseInt(txtCliente); 
            int idChef = Integer.parseInt(chef.getCedula());
            int idMesero = Integer.parseInt(mesero.getCedula());
            int idPlato = plato.getId();
            int idBebida = (bebida != null) ? bebida.getId() : 0;
            
            double total = plato.getPrecio() + ((bebida != null) ? bebida.getPrecio() : 0);

            if (sistema.registrarPedido(idPlato, idBebida, idChef, idMesero, idCliente, total)) {
                JOptionPane.showMessageDialog(vista, "¡Pedido Registrado!");
                actualizarTabla();
                vista.tfCliente.setText("");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar en BD.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El cliente (Mesa) debe ser un número.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage());
        }
    }

    private void entregarPedido() {
        int fila = vista.tablaResumen.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un pedido para entregar.");
            return;
        }
        
        int idPedido = (int) vista.tablaResumen.getValueAt(fila, 0);
        if (sistema.eliminarPedido(idPedido)) { // Llamada al modelo
            JOptionPane.showMessageDialog(vista, "Pedido Entregado.");
            actualizarTabla();
        }
    }

    private void cargarCombos() {
        try {
            vista.cbChef.removeAllItems();
            vista.cbMesero.removeAllItems();
            vista.cbPlato.removeAllItems();
            vista.cbBebida.removeAllItems();
            vista.tfCliente.setText("");

            for (Persona p : sistema.obtenerPersonalPorTipo("CHEF")) vista.cbChef.addItem(p);
            for (Persona p : sistema.obtenerPersonalPorTipo("MESERO")) vista.cbMesero.addItem(p);
            for (Alimento a : sistema.obtenerTodosAlimentos()) {
                if (a instanceof PlatoFuerte) vista.cbPlato.addItem(a);
                else if (a instanceof Bebida) vista.cbBebida.addItem(a);
            }
        } catch (Exception e) {
            System.out.println("Error cargando: " + e.getMessage());
        }
    }
    
    private void actualizarTabla() {
        vista.tablaResumen.setModel(sistema.obtenerResumenPedidos());
    }
}