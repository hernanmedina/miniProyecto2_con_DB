package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import vista.RegistroEquipoGUI;
import modelo.Equipo;
import modelo.EquipoDAO;

public class ControlRegistrarEquipoGUI implements ActionListener {
    
    private RegistroEquipoGUI vistaRegistroEquipo;
    private Equipo unEquipo;
    private EquipoDAO unEquipoDAO; 
    
    public ControlRegistrarEquipoGUI() {
        
        this.unEquipo = new Equipo();
        this.unEquipoDAO = new EquipoDAO();
        
        this.vistaRegistroEquipo = new RegistroEquipoGUI();
        this.vistaRegistroEquipo.setLocationRelativeTo(null);
        this.vistaRegistroEquipo.setVisible(true);
        
        this.vistaRegistroEquipo.jbtn_registrarEquipo.addActionListener(this);
        this.vistaRegistroEquipo.jbtn_modificar_equipo.addActionListener(this);
        this.vistaRegistroEquipo.jBtn_buscar_Equipo.addActionListener(this);
        this.vistaRegistroEquipo.jbtn_eliminar_equipo.addActionListener(this);
        this.vistaRegistroEquipo.jbtn_listar_Equipo.addActionListener(this);
    }

    @Override

    public void actionPerformed(ActionEvent e) {

        // Registrar Equipo
        if (e.getSource() == this.vistaRegistroEquipo.jbtn_registrarEquipo) {
            try {
                int noInventario = Integer.parseInt(vistaRegistroEquipo.jtf_numInventario.getText());
                int anhoCompra = Integer.parseInt(vistaRegistroEquipo.jtf_anioCompra.getText());
                String marca = vistaRegistroEquipo.jtf_marca_equipo.getText();
                
                //validamos que no sea null ó vacío
                if (marca == null || marca.isEmpty()) {
                    JOptionPane.showMessageDialog(vistaRegistroEquipo, "Debe ingresar un nombre. ");
                    return ;
                }

                unEquipo.setNoInventario(noInventario);
                unEquipo.setMarca(marca);
                unEquipo.setAnhoCompra(anhoCompra);

                if (unEquipoDAO.registrarEquipo(unEquipo)) {
                    JOptionPane.showMessageDialog(vistaRegistroEquipo, "Equipo registrado exitosamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vistaRegistroEquipo, "No se pudo registrar el equipo.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaRegistroEquipo,"Error: El numero de inventario y el año de compra debe ser un numero.");
            } catch (Exception ex){
                JOptionPane.showMessageDialog(vistaRegistroEquipo, "Error verifique los datos que ingreso.");
            }
        }

        // Buscar Equipo
        if (e.getSource() == this.vistaRegistroEquipo.jBtn_buscar_Equipo) {
            try {
                int noInventario = Integer.parseInt(vistaRegistroEquipo.jtf_numInventario.getText());
                unEquipo = unEquipoDAO.buscarEquipo(noInventario);

                if (unEquipo != null) {
                    vistaRegistroEquipo.jtf_marca_equipo.setText(unEquipo.getMarca());
                    vistaRegistroEquipo.jtf_anioCompra.setText(String.valueOf(unEquipo.getAnhoCompra()));
                } else {
                    JOptionPane.showMessageDialog(vistaRegistroEquipo, "Equipo no encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaRegistroEquipo, "Se requiere el numero de inventario para esta opción. \n" + ex);
            }
        }

        // Modificar Equipo
        if (e.getSource() == this.vistaRegistroEquipo.jbtn_modificar_equipo) {
            try {
                int noInventario = Integer.parseInt(vistaRegistroEquipo.jtf_numInventario.getText());
                int anhoCompra = Integer.parseInt(vistaRegistroEquipo.jtf_anioCompra.getText());
                String marca = vistaRegistroEquipo.jtf_marca_equipo.getText();
                
                //validamos que no sea null ó vacío
                if (marca == null || marca.isEmpty()) {
                    JOptionPane.showMessageDialog(vistaRegistroEquipo, "Debe ingresar un nombre. ");
                    return ;
                }

                unEquipo.setNoInventario(noInventario);
                unEquipo.setMarca(marca);
                unEquipo.setAnhoCompra(anhoCompra);

                if (unEquipoDAO.modificarEquipo(unEquipo)) {
                    JOptionPane.showMessageDialog(vistaRegistroEquipo, "Equipo modificado exitosamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vistaRegistroEquipo, "No se pudo modificar el equipo.");
                }
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaRegistroEquipo,"Se requiere el numero de inventario y año de compra para esta opción.");
            } catch (Exception ex){
                JOptionPane.showMessageDialog(vistaRegistroEquipo, "Error. Ingrese un nombre para el equipo. \n" + ex);
            }
        }

        // Listar Equipos
        if (e.getSource() == this.vistaRegistroEquipo.jbtn_listar_Equipo) {
            this.unEquipoDAO.listarEquipos(this.vistaRegistroEquipo.jTable_equiposComputo);
        }

        // Eliminar Equipo
        if (e.getSource() == this.vistaRegistroEquipo.jbtn_eliminar_equipo) {
            try {
                int noInventario = Integer.parseInt(vistaRegistroEquipo.jtf_numInventario.getText());

                if (unEquipoDAO.eliminarEquipo(noInventario)) {
                    JOptionPane.showMessageDialog(vistaRegistroEquipo, "Equipo eliminado exitosamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vistaRegistroEquipo, "No se pudo eliminar el equipo.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaRegistroEquipo, "Se requiere el numero de inventario para esta opción. \n" + ex);
            } 
        }
    }
    
    //Limpiar Campos
    public void limpiarCampos(){
        this.vistaRegistroEquipo.jtf_numInventario.setText("");
        this.vistaRegistroEquipo.jtf_marca_equipo.setText("");
        this.vistaRegistroEquipo.jtf_anioCompra.setText("");
    }
}
