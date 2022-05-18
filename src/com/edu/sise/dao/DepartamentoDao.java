
package com.edu.sise.dao;

import com.edu.sise.entidades.Departamento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DepartamentoDao {
    
    //Atributos
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    
    public DepartamentoDao()
    {
        cn = new Conexion().getConn();
    }
    public List<Departamento> getAll()
    {
        List<Departamento> lista = new ArrayList<>();
        
        String sql = "select * from departamentos";
        
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) //recorro mi tabla virtual
            {
                lista.add(new Departamento(
                        rs.getInt("id_depa"),
                        rs.getString("nombre")
                ));
            }
            
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
        
        return lista;
    }
    
    //metodo para agregar un registro en base de datos
    public boolean agregarDepartamento(Departamento o)
    {
        int cantidad = 0;
        //Crear nuestro script de agregar
        String sql = "insert into departamentos(nombre) values('"+o.getNombre()+"')";
        
        try {
            st = cn.createStatement();
        cantidad = st.executeUpdate(sql);
        } catch (Exception ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        }
       
        return (cantidad>0);
    }
    
    //metodo para modificar un registro en base de datos
    public boolean ModificarDepartamento(Departamento o)
    {
        int cantidad = 0;
        //Crear nuestro script de modificar
        String sql = "update departamentos set nombre = '"+o.getNombre()+"' where id_depa = " + o.getId_depa();
        
        try {
            st = cn.createStatement();
        cantidad = st.executeUpdate(sql);
        } catch (Exception ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        }
       
        return (cantidad>0);
    }
    
     //metodo para eliminar un registro en base de datos
    public boolean EliminarDepartamento(Integer id)
    {
        int cantidad = 0;
        //Crear nuestro script de eliminar
        String sql = "delete from departamentos where id_depa = " + id;
        
        try {
            st = cn.createStatement();
        cantidad = st.executeUpdate(sql);
        } catch (Exception ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        }
       
        return (cantidad>0);
    }
    
    
}
