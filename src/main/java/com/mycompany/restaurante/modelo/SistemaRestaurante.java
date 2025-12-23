package com.mycompany.restaurante.modelo;

import com.mycompany.restaurante.dao.AlimentoDAO;
import com.mycompany.restaurante.dao.PedidoDAO;
import com.mycompany.restaurante.dao.PersonaDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class SistemaRestaurante {
    
    private PersonaDAO personaDAO;
    private AlimentoDAO alimentoDAO;
    private PedidoDAO pedidoDAO;

    public SistemaRestaurante() {
        this.personaDAO = new PersonaDAO();
        this.alimentoDAO = new AlimentoDAO();
        this.pedidoDAO = new PedidoDAO();
    }

    public List<Persona> obtenerPersonalPorTipo(String tipo) {
        return personaDAO.listarPorTipo(tipo);
    }

    public List<Alimento> obtenerTodosAlimentos() {
        return alimentoDAO.listar();
    }

    public boolean registrarAlimento(Alimento alimento) {
        return alimentoDAO.registrar(alimento);
    }

    public boolean eliminarAlimento(int id) {
        return alimentoDAO.eliminar(id);
    }

    public boolean registrarPedido(int idPlato, int idBebida, int idChef, int idMesero, int idCliente, double total) {
        return pedidoDAO.registrarPedidoCompleto(idPlato, idBebida, idChef, idMesero, idCliente, total);
    }

    public boolean eliminarPedido(int idPedido) {
        return pedidoDAO.eliminarPedido(idPedido);
    }

    public DefaultTableModel obtenerResumenPedidos() {
        return pedidoDAO.obtenerResumenPedidos();
    }
}