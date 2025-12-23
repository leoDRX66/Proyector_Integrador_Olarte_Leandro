package com.mycompany.restaurante.controlador;

import com.mycompany.restaurante.modelo.*;
import com.mycompany.restaurante.vista.VistaAlimentos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorAlimentos implements ActionListener {
    private VistaAlimentos vista;
    private SistemaRestaurante sistema;

    public ControladorAlimentos(VistaAlimentos vista, SistemaRestaurante sistema) {
        this.vista = vista;
        this.sistema = sistema;
        
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        
        listarEnTabla();
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            guardarAlimento();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminarAlimento();
        }
    }

    private void guardarAlimento() {
        try {
            String nombre = vista.txtNombre.getText();
            String textoPrecio = vista.txtPrecio.getText();
            String tipo = (String) vista.cbTipo.getSelectedItem();

            if (nombre.isEmpty() || textoPrecio.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Complete todos los campos.");
                return;
            }

            double precio = Double.parseDouble(textoPrecio);
            Alimento nuevo = tipo.equals("Bebida") ? new Bebida(nombre, precio) : new PlatoFuerte(nombre, precio);

            if (sistema.registrarAlimento(nuevo)) { 
                JOptionPane.showMessageDialog(vista, "Alimento Guardado!");
                listarEnTabla();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar en la base de datos.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El precio debe ser un número válido.");
        }
    }

    private void eliminarAlimento() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un alimento de la tabla para eliminar.");
            return;
        }
        
        int id = (int) vista.tabla.getValueAt(fila, 0);
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Deseas eliminar?", "Eliminar", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            sistema.eliminarAlimento(id);
            listarEnTabla();
        }
    }

    private void listarEnTabla() {
        vista.modelo.setRowCount(0);
        for (Alimento a : sistema.obtenerTodosAlimentos()) {
            vista.modelo.addRow(new Object[]{
                a.getId(), a.getNombre(), a.getPrecio(), a.getClass().getSimpleName()
            });
        }
    }
    
    private void limpiarFormulario() {
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
    }
}