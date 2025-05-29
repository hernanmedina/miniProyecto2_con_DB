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
                JOptionPane.showMessageDialog(vistaRegistroProfesor, "Error. " + ex);
            }
        }
    }
}

