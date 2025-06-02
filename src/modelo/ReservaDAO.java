package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.*;

public class ReservaDAO {

    private ConexionDB conexionDB;
    private Reserva unaReserva;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public ReservaDAO() {
        this.conexionDB = new ConexionDB();
    }

    public boolean registrarReserva(Reserva reserva) {
        Connection con = conexionDB.obtenerConexion();
        String query = "INSERT INTO reserva (codigo, cedulaProfesor, noInventarioPC, fechaReserva, fechaRecogida, fechaEntrega) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, reserva.getCodigo());
            pst.setInt(2, reserva.getProfesor().getCedula());
            pst.setInt(3, reserva.getEquipo().getNoInventario());
            pst.setTimestamp(4, (Timestamp) reserva.getFechaReserva()); // Fecha actual al registrar reserva
            pst.setTimestamp(5, reserva.getHoraRecogida() != null ? (Timestamp) reserva.getHoraRecogida() : null); // Puede ser nulo
            pst.setTimestamp(6, reserva.getHoraEntrega() != null ? (Timestamp) reserva.getHoraEntrega() : null); // Puede ser nulo

            int resultado = pst.executeUpdate();
            return resultado > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar reserva: " + ex.getMessage());
            return false;
        }
    }

    // Actualizar hora de recogida
    public boolean actualizarHoraRecogida(int codigoReserva) {
        Connection con = conexionDB.obtenerConexion();
        String consulta = "SELECT fechaRecogida FROM reserva WHERE codigo = ?";

        try {
            pst = con.prepareStatement(consulta);
            pst.setInt(1, codigoReserva);
            rs = pst.executeQuery();

            if (rs.next()) {
                Timestamp fechaRecogida = rs.getTimestamp("fechaRecogida");

                if (fechaRecogida != null) {
                    JOptionPane.showMessageDialog(null, "Este equipo ya fue recogido. No se puede volver a registrar.");
                    return false;
                }
            }

            // Si fechaRecogida es NULL, actualiza la reserva con la hora actual
            String sqlUpdate = "UPDATE reserva SET fechaRecogida = ? WHERE codigo = ?";
            pst = con.prepareStatement(sqlUpdate);
            pst.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            pst.setInt(2, codigoReserva);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar hora de recogida: " + e.getMessage());
            return false;
        }
    }


    // Actualizar hora de entrega
    public boolean actualizarHoraEntrega(int codigoReserva) {
        Connection con = conexionDB.obtenerConexion();
        
        
        try {
            String query = "SELECT fechaEntrega FROM reserva WHERE codigo = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, codigoReserva);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                Timestamp fechaEntrega = rs.getTimestamp("fechaEntrega");

                if (fechaEntrega != null) {
                    JOptionPane.showMessageDialog(null, "Este equipo ya fue entregado.");
                    return false;
                }
            }
            
            // Si fechaEntrega es NULL, actualiza la reserva con la hora actual
            String sqlUpdate = "UPDATE reserva SET fechaEntrega = ? WHERE codigo = ?";
            pst = con.prepareStatement(sqlUpdate);
            pst.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            pst.setInt(2, codigoReserva);
            return pst.executeUpdate() > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    //Validar si existe la Reserva
    public boolean existeReserva(int codigo) {
        Connection conexion = conexionDB.obtenerConexion();
        String query = "SELECT * FROM reserva WHERE codigo = ?";
        try {
            pst = conexion.prepareStatement(query);
            pst.setInt(1, codigo);
            rs = pst.executeQuery();
            return rs.next();  // si hay algún resultado, ya existe
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar la reserva: " + ex.getMessage());
            return false;
        }
    }
    
    //Listar Reservas 
    public void listarReservas(JTable tabla) {
        String query = "SELECT p.cedula AS cedulaProfesor, eq.noInventario AS noInventarioPC, r.codigo, r.fechaReserva, r.fechaRecogida, r.fechaEntrega " +
               "FROM reserva r " +
               "JOIN profesor p ON r.cedulaProfesor = p.cedula " +
               "JOIN equipocomputo eq ON r.noInventarioPC = eq.noInventario " +
               "ORDER BY p.nombre ASC;";


        try {
            this.con = this.conexionDB.obtenerConexion();
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            // Crear modelo para la tabla
            javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            // Crear cabeceras de la tabla
            modelo.addColumn("Codigo");
            modelo.addColumn("Profesor");
            modelo.addColumn("Equipo");
            modelo.addColumn("fechaReserva");
            modelo.addColumn("fechaRecogida");
            modelo.addColumn("fechaEntrega");

            // Agregar datos al modelo
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getString("codigo");
                fila[1] = rs.getInt("cedulaProfesor");
                fila[2] = rs.getInt("noInventarioPC");
                fila[3] = rs.getTimestamp("fechaReserva");
                fila[4] = rs.getTimestamp("fechaRecogida");
                fila[5] = rs.getTimestamp("fechaEntrega");

                modelo.addRow(fila);
            }

            tabla.setModel(modelo);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + ex);
        }
    }
    
    // Eliminar Reserva 
    public boolean eliminarReserva(int codigo) {
       
        try {
            con = this.conexionDB.obtenerConexion();
  
            String queryObtener = "DELETE FROM reserva WHERE codigo = ?";
            pst = con.prepareStatement(queryObtener);
            pst.setInt(1, codigo);
            int resultado = pst.executeUpdate();

            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Eliminación realizada correctamente.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró una reserva con ese código.");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            //e.printStackTrace();
        }  
        return false;
    }

    //Consultar si tiene fecha de recogida establecida
    public boolean reservaTieneFechaRecogida(int codigoReserva) {
        Connection con = conexionDB.obtenerConexion();
        String sql = "SELECT fechaRecogida FROM reserva WHERE codigo = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, codigoReserva);
            rs = pst.executeQuery();

            if (rs.next()) {
                Timestamp fechaRecogida = rs.getTimestamp("fechaRecogida");
                return fechaRecogida != null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar reserva: " + e.getMessage());
        }
        return false;
    }
    
    //Acutualizar Reserva
    public boolean modificarReserva(Reserva reserva) {
        String query = "UPDATE reserva SET cedulaProfesor = ?, noInventarioPC = ?, fechaReserva = ?  WHERE codigo = ?";

        try {
            this.con = this.conexionDB.obtenerConexion();
            this.pst = this.con.prepareStatement(query);

            // Obtener valores correctos
            ProfesorDAO profesorDAO = new ProfesorDAO();
            EquipoDAO equipoDAO = new EquipoDAO();

            Profesor profesorEncontrado = profesorDAO.buscarProfesor(reserva.getProfesor().getCedula());
            int cedulaProfesor = profesorEncontrado != null ? profesorEncontrado.getCedula() : -1;

            Equipo equipoEncontrado = equipoDAO.buscarEquipo(reserva.getEquipo().getNoInventario());
            int noInventario = equipoEncontrado != null ? equipoEncontrado.getNoInventario() : -1;


            if (cedulaProfesor == -1) {
                JOptionPane.showMessageDialog(null, "El profesor no existe");
                return false;
            }

            if (noInventario == -1) {
                JOptionPane.showMessageDialog(null, "Equipo no encontrado.");
                return false;
            }

            // Asignamos valores correctos a los parámetros
            this.pst.setInt(1, cedulaProfesor);
            this.pst.setInt(2, noInventario);
            this.pst.setTimestamp(3, (Timestamp) reserva.getFechaReserva());
            this.pst.setInt(4, reserva.getCodigo()); // Código de la reserva en WHERE

            int resultado = this.pst.executeUpdate();

            return resultado > 0;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar reserva: " + ex.getMessage());
            return false;
        }
    }
    
    //obtener resrva por 
    public Reserva obtenerReservaPorCodigo(int codigoReserva) {
        Reserva reserva = null;

        Connection con = conexionDB.obtenerConexion();
        String sql = "SELECT r.codigo, r.fechaReserva, r.fechaEntrega, r.fechaRecogida, " +
             "p.cedula, p.nombre, p.apellido, p.curso, " +
             "e.noInventario, e.marca, e.anhoCompra " +
             "FROM reserva r " +
             "JOIN profesor p ON r.cedulaProfesor = p.cedula " +
             "JOIN equipocomputo e ON r.noInventarioPC = e.noInventario " +
             "WHERE r.codigo = ?";


        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, codigoReserva);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                
                Profesor profesor = new Profesor(
                        rs.getInt("cedula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("curso")
                );

                Equipo equipo = new Equipo(
                        rs.getInt("noInventario"),
                        rs.getString("marca"),
                        rs.getInt("anhoCompra")
                );

                reserva = new Reserva();
                reserva.setCodigo(rs.getInt("codigo"));
                reserva.setProfesor(profesor);
                reserva.setEquipo(equipo);
                reserva.setFechaReserva(rs.getTimestamp("fechaReserva"));
                reserva.setHoraEntrega(rs.getTimestamp("fechaEntrega"));
                reserva.setHoraRecogida(rs.getTimestamp("fechaRecogida"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener reserva: " + e.getMessage());
        }

        return reserva;
    }
    
   

    
}