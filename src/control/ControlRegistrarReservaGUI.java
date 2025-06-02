package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import modelo.Equipo;
import modelo.EquipoDAO;
import modelo.Profesor;
import modelo.ProfesorDAO;
import modelo.Reserva;
import modelo.ReservaDAO;
import vista.RegistroReservaGUI;

/**
 *
 * @author Hernan Medina
 */
public class ControlRegistrarReservaGUI implements ActionListener {
    
    private RegistroReservaGUI VistaReserva;
    private Reserva unaReserva;
    private ReservaDAO unaReservaDAO;
    
    private Timestamp fechaReserva;
    private Timestamp fechaNecesaria;
    private Timestamp horaRecogida;
    private Timestamp horaEntrega;

    
    public ControlRegistrarReservaGUI(){
        ProfesorDAO profesorDAO = new ProfesorDAO();
        EquipoDAO equipoDAO = new EquipoDAO();
        this.unaReservaDAO = new ReservaDAO();
        
        this.VistaReserva = new RegistroReservaGUI();
        this.VistaReserva.setLocationRelativeTo(null);
        this.VistaReserva.setVisible(true);
        
        this.VistaReserva.jbtn_Registra_reserva.addActionListener(this);
        this.VistaReserva.jbtn_buscar_reserva.addActionListener(this);
        this.VistaReserva.jbtn_Listar_Reserva.addActionListener(this);
        this.VistaReserva.jbtn_modificar_reserva.addActionListener(this);
        this.VistaReserva.jbtn_eliminar_reserva.addActionListener(this);
        this.VistaReserva.jbtn_recoger_EQ.addActionListener(this);
        this.VistaReserva.jbtn_entregar_EQ.addActionListener(this);
        
        //Llenar ComboBox profesor
        this.VistaReserva.jComb_profesor.removeAllItems();
        for  (Profesor prof : profesorDAO.obtenerProfesor()){
            this.VistaReserva.jComb_profesor.addItem(prof);
        }
        
        //Llenar ComboBox equipo
        this.VistaReserva.jComb_equipo.removeAllItems();
        for (Equipo eq : equipoDAO.obtenerEquipo() ){
            this.VistaReserva.jComb_equipo.addItem(eq);
        }

    }
    
    //Validamos si fecha de entrega es diferente de null
    public void validarBotonEntrega() {
        int codigoReserva;
        try {
            codigoReserva = Integer.parseInt(this.VistaReserva.jTf_Reserva_codigo.getText());
        } catch (NumberFormatException ex) {
            VistaReserva.jbtn_entregar_EQ.setEnabled(false);
            return;
        }

        boolean tieneFechaRecogida = unaReservaDAO.reservaTieneFechaRecogida(codigoReserva);
        VistaReserva.jbtn_entregar_EQ.setEnabled(tieneFechaRecogida);
    }
    
    //Consultar reserva
    public void consultarReserva(Reserva reserva){
        if (reserva != null) {
            JOptionPane.showMessageDialog(null, reserva.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la reserva.");
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Boton agregar reserva
        if (e.getSource() == this.VistaReserva.jbtn_Registra_reserva) {
            
            try{
                int codigo =Integer.parseInt(this.VistaReserva.jTf_Reserva_codigo.getText());
                Profesor prof = (Profesor) this.VistaReserva.jComb_profesor.getSelectedItem();
                Equipo eq = (Equipo) this.VistaReserva.jComb_equipo.getSelectedItem();
                String fechaTexto = this.VistaReserva.jtf_Fecha_reserva.getText();
                Timestamp fechaReserva = Timestamp.valueOf(fechaTexto);
                Timestamp horaRecogida = null;
                Timestamp horaEntrega = null;
                
                //validamos si ya existe un profesor
                if (unaReservaDAO.existeReserva(codigo)) {
                    JOptionPane.showMessageDialog(VistaReserva, "La reserva ya existe!.");
                    //limpiarCampos();
                    return;
                }

                unaReserva = new Reserva(prof, eq, codigo, fechaReserva, horaRecogida, horaEntrega);


                if (unaReservaDAO.registrarReserva(unaReserva)) {
                    JOptionPane.showMessageDialog(VistaReserva, "¡Reserva registrada con éxito!");
                    validarBotonEntrega();
                } else {
                    JOptionPane.showMessageDialog(VistaReserva, "Error al registrar reserva.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(VistaReserva, "Error: El campo codigo no debe de estar vacío \n y no se aceptan letras solo numeros. " );
            } catch (Exception ex){
                JOptionPane.showMessageDialog(VistaReserva, "Error "+ ex);
            }
        }
        
        // Botón Recoger Equipo
        if (e.getSource() == this.VistaReserva.jbtn_recoger_EQ) {
            
            try {
                int codigoReserva = Integer.parseInt(this.VistaReserva.jTf_Reserva_codigo.getText()); // Obtener código desde un campo de texto
                if (unaReservaDAO.actualizarHoraRecogida(codigoReserva)) {
                    JOptionPane.showMessageDialog(null, "Hora de recogida actualizada exitosamente.");
                    validarBotonEntrega();
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar la hora de recogida.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: El campo codigo no debe de estar vacío \n y no se aceptan letras solo numeros. ");
            }
        }

        // Botón Entregar Equipo
        if (e.getSource () == this.VistaReserva.jbtn_entregar_EQ) {
            validarBotonEntrega();
            try {
                int codigoReserva = Integer.parseInt(this.VistaReserva.jTf_Reserva_codigo.getText());

                if (!VistaReserva.jbtn_entregar_EQ.isEnabled()) {
                    JOptionPane.showMessageDialog(null, "El equipo aún no ha sido recogido por el profesor.");
                    return;
                }

                if (unaReservaDAO.actualizarHoraEntrega(codigoReserva)) {
                    JOptionPane.showMessageDialog(null, "Hora de entrega actualizada exitosamente.");
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar la hora de entrega.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: El campo código no debe estar vacío \n y solo se aceptan números.");
            }
        }


        
        //Boton Listar Reservas
        if (e.getSource()== this.VistaReserva.jbtn_Listar_Reserva) {
            
            try {
                this.unaReservaDAO.listarReservas(this.VistaReserva.jTable_reservas);
                validarBotonEntrega();

            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(VistaReserva, "Error: El campo codigo no debe de estar vacío \n y no se aceptan letras solo numeros. " );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(VistaReserva, "Error NO hay reservas para listar. " + ex);
            }
        }
        
        //Boton Eliminar Reserva
        if (e.getSource()== this.VistaReserva.jbtn_eliminar_reserva) {
            
            try {
                int idBuscado = Integer.parseInt(VistaReserva.jTf_Reserva_codigo.getText());
                this.unaReservaDAO.eliminarReserva(idBuscado);
                validarBotonEntrega();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(VistaReserva, "Error: El campo codigo no debe de estar vacío \n y no se aceptan letras solo numeros. " );
            }
        }
        
        //Boton actualizar reserva
       if (e.getSource() == this.VistaReserva.jbtn_modificar_reserva) {
            try {
                int idBuscado = Integer.parseInt(VistaReserva.jTf_Reserva_codigo.getText());

                // Obtener la reserva existente
                Reserva reserva = unaReservaDAO.obtenerReservaPorCodigo(idBuscado);

                if (reserva == null) {
                    JOptionPane.showMessageDialog(VistaReserva, "No se encontró una reserva con ese código.");
                    return;
                }

                // Modificar la reserva con nuevos datos del formulario
                Profesor prof = (Profesor) VistaReserva.jComb_profesor.getSelectedItem();
                Equipo eq = (Equipo) VistaReserva.jComb_equipo.getSelectedItem();
                Timestamp fechaEntrega = (Timestamp) reserva.getHoraEntrega();
                Timestamp fechaRecogida = (Timestamp) reserva.getHoraRecogida();
                Timestamp fechaReserva = (Timestamp) reserva.getFechaReserva();

                reserva.setProfesor(prof);
                reserva.setEquipo(eq);
                reserva.setHoraEntrega(fechaEntrega);
                reserva.setHoraRecogida(fechaRecogida);
                reserva.setFechaReserva(fechaReserva);

                if (unaReservaDAO.modificarReserva(reserva)) {
                    JOptionPane.showMessageDialog(VistaReserva, "Reserva modificada exitosamente.");
                    validarBotonEntrega();
                } else {
                    JOptionPane.showMessageDialog(VistaReserva, "Error al modificar la reserva.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(VistaReserva, "Error: El campo código no debe estar vacío y solo se aceptan números.");
            }
        }
       
       //Boton consultar
       if (e.getSource() == this.VistaReserva.jbtn_buscar_reserva) {
            try {
                int codigo = Integer.parseInt(VistaReserva.jTf_Reserva_codigo.getText());
                Reserva reserva = unaReservaDAO.obtenerReservaPorCodigo(codigo);
                consultarReserva(reserva); // ← este método debe estar en el mismo controlador

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(VistaReserva,
                        "Error: El campo código no debe estar vacío y solo se aceptan números.");
            }
        }

    }
    
}
