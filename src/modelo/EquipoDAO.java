package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EquipoDAO {
    
    private ConexionDB conexionDB;

    public EquipoDAO() {
        this.conexionDB = new ConexionDB();
    }

    // Registrar equipo
    public boolean registrarEquipo(Equipo equipo) {
        Connection conexion = conexionDB.obtenerConexion();
        String sql = "INSERT INTO equipocomputo (noInventario, marca, anhoCompra) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, equipo.getNoInventario());
            ps.setString(2, equipo.getMarca());
            ps.setInt(3, equipo.getAnhoCompra());

            int resultado = ps.executeUpdate();
            return resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error al registrar equipo: " + ex.getMessage());
            return false;
        }
    }

    // Buscar equipo por NoInventario
    public Equipo buscarEquipo(int noInventario) {
        Connection conexion = conexionDB.obtenerConexion();
        String sql = "SELECT * FROM equipocomputo WHERE noInventario = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, noInventario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setNoInventario(rs.getInt("noInventario"));
                equipo.setMarca(rs.getString("marca"));
                equipo.setAnhoCompra(rs.getInt("anhoCompra"));
                return equipo;
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar equipo: " + ex.getMessage());
        }
        return null;
    }

    // Modificar equipo
    public boolean modificarEquipo(Equipo equipo) {
        Connection conexion = conexionDB.obtenerConexion();
        String sql = "UPDATE equipocomputo SET marca = ?, anhoCompra = ? WHERE noInventario = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, equipo.getMarca());
            ps.setInt(2, equipo.getAnhoCompra());
            ps.setInt(3, equipo.getNoInventario());

            int resultado = ps.executeUpdate();
            return resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error al modificar equipo: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar equipo
    public boolean eliminarEquipo(int noInventario) {
        Connection conexion = conexionDB.obtenerConexion();
        String sql = "DELETE FROM equipocomputo WHERE noInventario = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, noInventario);

            int resultado = ps.executeUpdate();
            return resultado > 0;

        } catch (SQLException ex) {
            System.out.println("Error al eliminar equipo: " + ex.getMessage());
            return false;
        }
    }

    // Listar todos los equipos
    public String listarEquipos() {
        Connection conexion = conexionDB.obtenerConexion();
        String sql = "SELECT * FROM equipocomputo";
        StringBuilder listado = new StringBuilder();

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listado.append("No Inventario: ").append(rs.getInt("noInventario"))
                       .append(" | Marca: ").append(rs.getString("marca"))
                       .append(" | Año de Compra: ").append(rs.getInt("anhoCompra"))
                       .append("\n");
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar equipos: " + ex.getMessage());
        }
        return listado.toString();
    }
}

