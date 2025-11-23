package com.mycompany.restaurante.dao;

import com.mycompany.restaurante.modelo.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    public List<Persona> listarPorTipo(String tipo) {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM personas WHERE tipo_persona = ?";
        
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, tipo);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Persona p = new Persona(rs.getString("nombre"), rs.getString("cedula"), rs.getString("telefono"));
                p.setCedula(String.valueOf(rs.getInt("id")));
                p.setNombre(rs.getString("nombre"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}