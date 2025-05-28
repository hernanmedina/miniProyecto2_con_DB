/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Hernan Medina
 */
public class Equipo {
    private int noInventario;
    private String marca;
    private int anhoCompra;

    // Constructor vacío
    public Equipo() {}

    // Constructor con parámetros
    public Equipo(int numeroInventario, String marca, int anoCompra) {
        this.noInventario = numeroInventario;
        this.marca = marca;
        this.anhoCompra = anoCompra;
    }

    // Getters
    public int getNoInventario() {
        return noInventario;
    }

    public String getMarca() {
        return marca;
    }

    public int getAnhoCompra() {
        return anhoCompra;
    }
    
    //Setters
    public void setNoInventario(int noInventario) {
        this.noInventario = noInventario;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setAnhoCompra(int anhoCompra) {
        this.anhoCompra = anhoCompra;
    }
}

