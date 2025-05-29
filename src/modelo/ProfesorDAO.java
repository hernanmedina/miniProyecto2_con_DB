package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Hernan Medina
 */
public class ProfesorDAO {
    private ConexionDB conexionDB;
    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    
    public ProfesorDAO(){
        this.conexionDB = new ConexionDB();
    }
    
    //Registrar profesor
    public boolean registrarProfesor(Profesor profesor){
        Connection conexion = conexionDB.obtenerConexion();
        String query = "INSERT INTO profesor (cedula, nombre, apellido, curso) VALUES (?,?,?,?)";
        try{
            pst= conexion.prepareStatement(query);
            pst.setInt(1, profesor.getCedula());
            pst.setString(2, profesor.getNombre());
            pst.setString(3, profesor.getApellido());
            pst.setString(4, profesor.getCurso());
            
            int resultado = pst.executeUpdate();
            return resultado > 0;
        
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error al registrar profesor"+ ex.getMessage());
            return false;
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Error cerrando PreparedStatement: " + e.getMessage());
            }
        }
    }  
    
    //verifique la existencia de un profesor
    public boolean existeProfesor(int cedula) {
        Connection conexion = conexionDB.obtenerConexion();
        String query = "SELECT * FROM profesor WHERE cedula = ?";
        try {
            pst = conexion.prepareStatement(query);
            pst.setInt(1, cedula);
            rs = pst.executeQuery();
            return rs.next();  // si hay algún resultado, ya existe
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar profesor: " + ex.getMessage());
            return false;
        }
    }
    
    //Listar profesores
    public void listarProfesores(JTable tabla){
        Connection conexion = conexionDB.obtenerConexion();
        String query = "SELECT * FROM profesor";
        
        try {
            this.con =this.conexionDB.obtenerConexion();
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            
            javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel();
            modelo.addColumn("Cedula");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Curso");
            
            while (rs.next()){
                Object[] fila = new Object[4];
                fila[0] = rs.getInt("cedula");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getString("curso");
                modelo.addRow(fila);
            }
            tabla.setModel(modelo);
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al listar los profesores"+ ex);
        }
    }

}
