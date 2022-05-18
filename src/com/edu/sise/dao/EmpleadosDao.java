
package com.edu.sise.dao;
import com.edu.sise.entidades.Empleados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class EmpleadosDao {
    
    //Atributos
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public EmpleadosDao()
    {
        cn = new Conexion().getConn();
    }
    
    private Empleados getRS(ResultSet record) throws SQLException
    {
        return new Empleados(
                        record.getInt("id_empleado"),
                        record.getString("dni"),
                        record.getString("nombres"),
                        record.getString("apellidos"));
    }
      
    public List<Empleados> getAll()
    {
        List<Empleados> lista = new ArrayList<>();
        
        String sql = "select * from empleado";
        
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) lista.add(getRS(rs));
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
        
        return lista;
    }
    
    //metodo para agregar un empleado en base de datos
    public boolean agregarEmpleados(Empleados E)
    {
        int cantidad = 0;
        //Crear nuestro script de agregar
        String sql = "insert into empleado(dni,nombres,apellidos) values(?,?,?)"; 
        try {
         ps = cn.prepareStatement(sql);
         ps.setString(1, E.getDni());
         ps.setString(2, E.getNombre());
         ps.setString(3, E.getApellido());
         cantidad = ps.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        }
       
        return (cantidad>0);
    }
    
    //metodo para modificar un EMPLEADO en base de datos
    public boolean ModificarEmpleados(Empleados E)
    {
        int cantidad = 0;
        //Crear nuestro script de modificar
        String sql = "update empleado set dni = ?, nombres = ?, apellidos = ? where id_empleado = ?";
        
        try {
            ps = cn.prepareStatement(sql);
          int x = 1;       
          ps.setString(x++, E.getDni());
          ps.setString(x++, E.getNombre());
          ps.setString(x++, E.getApellido());
          ps.setInt(x++, E.getId_empleados());
          cantidad = ps.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        }
       
        return (cantidad>0);
    }
    
    //metodo para eliminar un empleado en base de datos
    public boolean EliminarEmpleados(Integer id)
    {
        int cantidad = 0;
        //Crear nuestro script de eliminar
        String sql = "delete from empleado where id_empleado = ?";
        
        try {
          ps = cn.prepareStatement(sql);
          ps.setInt(1, id);
          cantidad = ps.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        }
       
        return (cantidad>0);
    }
    
     public List<Empleados> getSearch(String nombre)
    {
        List<Empleados> lista = new ArrayList<>();      
        String sql = "select * from empleado where nombres like ?";
        
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, "%"+nombre+"%");
            rs = ps.executeQuery();
            while (rs.next()) lista.add(getRS(rs));
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
        
        return lista;
    }
}
