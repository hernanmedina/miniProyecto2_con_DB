package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class EquipoDAO {
    
    private ConexionDB conexionDB;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

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
            JOptionPane.showMessageDialog(null, "Error al registrar equipo: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Error al buscar equipo: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Error al modificar equipo: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Error al eliminar equipo: " + ex.getMessage());
            return false;
        }
    }

    // Listar todos los equipos
    public void listarEquipos(JTable tabla) {
        Connection conexion = conexionDB.obtenerConexion();
  
         String query = "SELECT * FROM equipocomputo";

        try {
            this.con = this.conexionDB.obtenerConexion();
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
            modelo.addColumn("Inventario");
            modelo.addColumn("Marca");
            modelo.addColumn("año_comp");

            while (rs.next()) {
                Object[] fila = new Object[3];
                fila[0] = rs.getInt("noInventario");
                fila[1] = rs.getString("marca");
                fila[2] = rs.getString("anhoCompra"); 
                modelo.addRow(fila);
            }
            tabla.setModel(modelo);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar equipos: " + ex.getMessage());
        }
    }
}

