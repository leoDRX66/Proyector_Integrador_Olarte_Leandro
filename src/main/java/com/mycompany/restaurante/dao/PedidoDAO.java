package com.mycompany.restaurante.dao;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class PedidoDAO {
    public boolean registrarPedidoCompleto(int idPlato, int idBebida, int idChef, int idMesero, int numeroMesa, double total) {
        String sql = "INSERT INTO pedidos_detalle (id_plato, id_bebida, id_chef, id_mesero, id_cliente, total) VALUES (?,?,?,?,?,?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPlato);
            ps.setInt(2, idBebida);
            ps.setInt(3, idChef);
            ps.setInt(4, idMesero);
            ps.setInt(5, numeroMesa);
            ps.setDouble(6, total);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPedido(int id) {
        String sql = "DELETE FROM pedidos_detalle WHERE id = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }

    public DefaultTableModel obtenerResumenPedidos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Mesa N°");
        modelo.addColumn("Mesero");
        modelo.addColumn("Chef");
        modelo.addColumn("Plato");
        modelo.addColumn("Bebida");
        modelo.addColumn("Total ($)");

        String sql = "SELECT pd.id, pd.id_cliente as mesa, mes.nombre as mesero, chef.nombre as chef, " +
                     "ali.nombre as plato, beb.nombre as bebida, pd.total " +
                     "FROM pedidos_detalle pd " +
                     "LEFT JOIN personas mes ON pd.id_mesero = mes.id " +
                     "LEFT JOIN personas chef ON pd.id_chef = chef.id " +
                     "LEFT JOIN alimentos ali ON pd.id_plato = ali.id " +
                     "LEFT JOIN alimentos beb ON pd.id_bebida = beb.id " +
                     "ORDER BY pd.id DESC";

        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("mesa"),
                    rs.getString("mesero"),
                    rs.getString("chef"),
                    rs.getString("plato"),
                    rs.getString("bebida"),
                    rs.getDouble("total")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }
        return modelo;
    }
}