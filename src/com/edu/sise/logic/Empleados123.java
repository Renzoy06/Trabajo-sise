
package com.edu.sise.logic;

import com.edu.sise.dao.EmpleadosDao;
import com.edu.sise.entidades.Empleados;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class Empleados123 {
    
    List<Empleados> lista;
    
    public DefaultTableModel getModelo()
    {
        //cargarlista();
        lista = new EmpleadosDao().getAll();
        DefaultTableModel modelo =  new DefaultTableModel();
        //crear mis columnas
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        for(Empleados obj: lista)
        {
            Object data[] =
            {
                obj.id_empleados,
                obj.dni,
                obj.nombre,
                obj.apellido,
              
            };
            
            modelo.addRow(data);
        }          
        return  modelo;
    }
    
    public DefaultTableModel getModelo(String nombre)
    {
        //cargarlista();
        lista = new EmpleadosDao().getSearch(nombre);
        DefaultTableModel modelo =  new DefaultTableModel();
        //crear mis columnas
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("APELLIDO");
        for(Empleados obj: lista)
        {
            Object data[] =
            {
                obj.getId_empleados(),
                obj.getDni(),
                obj.getNombre(),
                obj.getApellido(),
            };
            
            modelo.addRow(data);
        }          
        return  modelo;
    }

    
     public void imprimir(JTable tabla)
    {
        tabla.setModel(getModelo());
    }
     
     public void imprimir(JTable tabla, String nombre)
    {
        tabla.setModel(getModelo(nombre));
    }
    
    public boolean agregarEmpleados(Empleados E)
    {
        return new EmpleadosDao().agregarEmpleados(E);
    }
    
    public boolean modificarEmpleados(Empleados E)
    {
        return new EmpleadosDao().ModificarEmpleados(E);
    }
    
    public boolean EliminarEmpleados(Integer id)
    {
        return new EmpleadosDao().EliminarEmpleados(id);
    }

    
}
