package padula.delaiglesia.dam.isi.frsf.lab0002.modelo;

import java.util.Date;

/**
 * Created by st on 07/09/2017.
 */
public class Tarjeta implements java.io.Serializable{
    private  String nombre;
    private Integer numero;
    private  Integer seguridad;
    private String fechaVencimiento;

    @Override
    public String toString() {
        return "Tarjeta{" +
                "nombre='" + nombre + '\'' +
                ", numero=" + numero +
                ", seguridad=" + seguridad +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setSeguridad(Integer seguridad) {
        this.seguridad = seguridad;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getSeguridad() {
        return seguridad;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Tarjeta(Integer numero, String nombre, Integer seguridad, String fechaVencimiento) {
        this.numero = numero;
        this.nombre = nombre;
        this.seguridad = seguridad;
        this.fechaVencimiento = fechaVencimiento;
    }
}
