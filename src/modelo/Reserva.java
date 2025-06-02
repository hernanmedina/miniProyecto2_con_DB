/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Date;
import modelo.Reserva;

/**
 *
 * @author Hernan Medina
 */
public class Reserva {
    private Profesor profesor;
    private Equipo equipo;
    private int codigo;
    private Date fechaReserva;
    private Date horaRecogida;
    private Date horaEntrega;

    // Constructor
    public Reserva(Profesor profesor, Equipo equipo, int codigo, Date fechaReserva, Date horaRecogida, Date horaEntrega) {
        this.profesor = profesor;
        this.equipo = equipo;
        this.codigo = codigo;
        this.fechaReserva = fechaReserva;
        this.horaRecogida = horaRecogida;
        this.horaEntrega = horaEntrega;
    }

    @Override
    public String toString() {
        return "Reserva{" + "profesor=" + profesor + ", equipo=" + equipo + ",\n codigo=" + codigo + ", fechaReserva=" + fechaReserva + ", horaRecogida=" + horaRecogida + ", horaEntrega=" + horaEntrega + '}';
    }

    Reserva() {
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public Date getHoraRecogida() {
        return horaRecogida;
    }

    public Date getHoraEntrega() {
        return horaEntrega;
    }
    
    public void setProfesor(Profesor profesor) {
    this.profesor = profesor;
}

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setHoraEntrega(Date horaEntrega) {
        this.horaEntrega = horaEntrega;
    }
    
    public void setHoraRecogida(Date horaRecogida){
        this.horaRecogida = horaRecogida;
    }
}

