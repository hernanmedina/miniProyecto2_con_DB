package control;

import vista.Menu_ReservaGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.RegistroReservaGUI;

public class ControlMenuReservasGUI implements ActionListener {
    private Menu_ReservaGUI vistaMenu;
    private RegistroReservaGUI vistaReserva;


    public ControlMenuReservasGUI() {
        if (this.vistaMenu == null) {
            this.vistaMenu = new Menu_ReservaGUI();
        }
        this.vistaMenu.setVisible(true);
        this.vistaMenu.jBtn_vista_Equipo.addActionListener(this);
        this.vistaMenu.jBtn_vista_Reserva.addActionListener(this);
        this.vistaMenu.jBtn_vista_Profesor.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaMenu.jBtn_vista_Equipo) {
            new ControlRegistrarEquipoGUI();
            
        } else if (e.getSource() == this.vistaMenu.jBtn_vista_Profesor) {
           // new ControlRegistroProfesorGUI();
            
        }else if (e.getSource() == this.vistaMenu.jBtn_vista_Reserva) {
            new ControlRegistrarReservaGUI();
        }
    }
}
