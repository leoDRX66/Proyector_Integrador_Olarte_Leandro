package com.mycompany.restaurante.dao;

import com.mycompany.restaurante.modelo.Alimento;
import com.mycompany.restaurante.modelo.PlatoFuerte;
import com.mycompany.restaurante.modelo.Bebida;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO {
    
    public boolean registrar(Alimento a) {
        String sql = "INSERT INTO alimentos (nombre, precio, tipo_alimento) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setDouble(2, a.getPrecio());
            ps.setString(3, a.getClass().getSimpleName()); 
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM alimentos WHERE id = ?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public List<Alimento> listar() {
        List<Alimento> lista = new ArrayList<>();
        String sql = "SELECT * FROM alimentos";
        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String tipo = rs.getString("tipo_alimento");
                Alimento a;
                if ("Bebida".equalsIgnoreCase(tipo)) {
                    a = new Bebida();
                } else {
                    a = new PlatoFuerte();
                }
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setPrecio(rs.getDouble("precio"));
                lista.add(a);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}