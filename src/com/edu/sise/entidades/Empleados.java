
package com.edu.sise.entidades;


public class Empleados {

    public Empleados(Integer id_empleados, String dni, String nombre, String apellido) {
        this.id_empleados = id_empleados;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getId_empleados() {
        return id_empleados;
    }

    public void setId_empleados(Integer id_empleados) {
        this.id_empleados = id_empleados;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public Integer id_empleados;
    public String dni;
    public String nombre;
    public String apellido;
    
    
}
