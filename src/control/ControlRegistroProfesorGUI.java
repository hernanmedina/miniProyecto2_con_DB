package control;

import modelo.Profesor;
import vista.RegistroProfesorGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ProfesorDAO;


public class ControlRegistroProfesorGUI implements ActionListener {
    private RegistroProfesorGUI vistaRegistroProfesor;
    private Profesor unProfesor;
    private ProfesorDAO unProfesorDAO; 
    
    public ControlRegistroProfesorGUI() {
        
        this.unProfesor = new Profesor();
        this.unProfesorDAO = new ProfesorDAO();
        
        this.vistaRegistroProfesor = new RegistroProfesorGUI();
        this.vistaRegistroProfesor.setLocationRelativeTo(null);
        this.vistaRegistroProfesor.setVisible(true);
        
        
        this.vistaRegistroProfesor.jbtn_registrar_Profesor.addActionListener(this);
        this.vistaRegistroProfesor.jbtn_modificar_Profesor.addActionListener(this);
        this.vistaRegistroProfesor.jBtn_buscar_profesor.addActionListener(this);
        this.vistaRegistroProfesor.jbtn_eliminar_profesor.addActionListener(this);
        this.vistaRegistroProfesor.jbtn_listar_Profesores.addActionListener(this);
    }
    
    public void limpiarCampos(){
        vistaRegistroProfesor.jtf_cedula.setText("");
        vistaRegistroProfesor.jtf_nombre.setText("");
        vistaRegistroProfesor.jtf_apellido.setText("");
        vistaRegistroProfesor.jtf_curso.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Agregar Profesor
        if (e.getSource() == this.vistaRegistroProfesor.jbtn_registrar_Profesor) {
            try {
                int cedula = Integer.parseInt(vistaRegistroProfesor.jtf_cedula.getText());
                String nombre = vistaRegistroProfesor.jtf_nombre.getText();
                String apellido = vistaRegistroProfesor.jtf_apellido.getText();
                String curso = vistaRegistroProfesor.jtf_curso.getText();
                
                unProfesor.setCedula(cedula);
                unProfesor.setNombre(nombre);
                unProfesor.setApellido(apellido);
                unProfesor.setCurso(curso);
                
                //validamos que los textfile no esten vacios
                if (vistaRegistroProfesor.jtf_cedula.getText().isEmpty()
                        || vistaRegistroProfesor.jtf_nombre.getText().isEmpty()
                        || vistaRegistroProfesor.jtf_apellido.getText().isEmpty()
                        || vistaRegistroProfesor.jtf_curso.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(vistaRegistroProfesor, "Por favor, complete todos los campos.");
                    return;
                }

                //validamos si ya existe un profesor
                if (unProfesorDAO.existeProfesor(cedula)) {
                    JOptionPane.showMessageDialog(vistaRegistroProfesor, "El profesor ya está registrado.");
                    limpiarCampos();
                    return;
                }

                //registramos el profesor
                if (unProfesorDAO.registrarProfesor(unProfesor)) {
                    JOptionPane.showMessageDialog(vistaRegistroProfesor, "¡Profesor registrado con exito!");
                    this.unProfesorDAO.listarProfesores(this.vistaRegistroProfesor.jTable_profesores);
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vistaRegistroProfesor, "¡No se pudo realizar el registro!");
                }
                limpiarCampos();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaRegistroProfesor, "Error: La cedula debe ser un numero.");
            }
        }
        
        //Listar profesores
        if (e.getSource()== this.vistaRegistroProfesor.jbtn_listar_Profesores) {
            try {
                this.unProfesorDAO.listarProfesores(this.vistaRegistroProfesor.jTable_profesores);
            
            } catch(Exception ex){
                JOptionPane.showMessageDialog(vistaRegistroProfesor, "Error NO hay profesores para listar. " + ex);
            }
        }
        
        //Buscar Profesor
        if (e.getSource()== this.vistaRegistroProfesor.jBtn_buscar_profesor) {
            try {
                int cedula = Integer.parseInt(this.vistaRegistroProfesor.jtf_cedula.getText());
                unProfesor = unProfesorDAO.buscarProfesor(cedula);
                
                //validamos que no sea null
                if (unProfesor != null) {
                    vistaRegistroProfesor.jtf_nombre.setText(unProfesor.getNombre());
                    vistaRegistroProfesor.jtf_apellido.setText(unProfesor.getApellido());
                    vistaRegistroProfesor.jtf_curso.setText(unProfesor.getCurso());
                }
            
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(vistaRegistroProfesor,"Error: La cedula debe ser un numero.");
            } catch (Exception ex){
                JOptionPane.showMessageDialog(vistaRegistroProfesor, "Error verifique los datos que ingreso.");
            }
        }
        
        //Eliminar Profesor
        if (e.getSource() == this.vistaRegistroProfesor.jbtn_eliminar_profesor) {
            try {
                int cedula = Integer.parseInt(vistaRegistroProfesor.jtf_cedula.getText());

                if (unProfesorDAO.EliminarProfesor(cedula)) {
                    JOptionPane.showMessageDialog(vistaRegistroProfesor, "Equipo eliminado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(vistaRegistroProfesor, "No se pudo eliminar el equipo.");
                }
            } catch (Exception ex){
                JOptionPane.showMessageDialog(vistaRegistroProfesor, "Error! No sepudo eliminar, verifique los datos que ingreso.");
            }
        }
        
        //Modificar profesor
        if (e.getSource() == this.vistaRegistroProfesor.jbtn_modificar_Profesor) {
            try {
                int cedula= Integer.parseInt(this.vistaRegistroProfesor.jtf_cedula.getText());
                String nombre = this.vistaRegistroProfesor.jtf_nombre.getText();
                String apellido = this.vistaRegistroProfesor.jtf_apellido.getText();
                String curso = this.vistaRegistroProfesor.jtf_curso.getText();
                
                unProfesor.setNombre(nombre);
                unProfesor.setApellido(apellido);
                unProfesor.setCurso(curso);
                
                    if (unProfesorDAO.modificarProfesor(unProfesor)) {
                        JOptionPane.showMessageDialog(vistaRegistroProfesor, "Datos modificados correctamente. ");
                    }else {
                        //validamos si ya existe un profesor
                        if (!unProfesorDAO.existeProfesor(cedula)) {
                            JOptionPane.showMessageDialog(vistaRegistroProfesor, "El profesor NO está registrado.");
                            return;
                        }
                        JOptionPane.showMessageDialog(vistaRegistroProfesor, "Todos los campos son obligatorios. ");
                    }
                
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(vistaRegistroProfesor,"Error: La cedula debe ser un numero.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vistaRegistroProfesor, "Error valide los datos, todos los campos son requeridos.");
            }
        }
    }
}

