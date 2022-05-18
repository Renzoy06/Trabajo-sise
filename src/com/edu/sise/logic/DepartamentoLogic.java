
package com.edu.sise.logic;


import com.edu.sise.dao.DAOManager;
import com.edu.sise.dao.DepartamentoDao;
import com.edu.sise.entidades.Departamento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DepartamentoLogic {
    
    List<Departamento> lista;
    DepartamentoDao dao = DAOManager.getInstancia().getDepartamentoDao();
    
    
    public DefaultTableModel getModelo()
    {
        //cargarlista();
        lista = dao.getAll();
        DefaultTableModel modelo =  new DefaultTableModel();
        //crear mis columnas
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        for(Departamento obj: lista)
        {
            Object data[] =
            {
                obj.getId_depa(),
                obj.getNombre()
            };
            
            modelo.addRow(data);
        }          
        return  modelo;
    }
    
    /*public void cargarlista()
    {
        lista = new ArrayList<>();
        lista.add(new Departamento(100, "La Libertad"));
        lista.add(new Departamento(200, "Lima"));
        lista.add(new Departamento(300, "Tumbes"));
        lista.add(new Departamento(400, "Lambayeque"));
        
        
    }*/
    
    public void imprimir(JTable tabla)
    {
        tabla.setModel(getModelo());
    }
    
    public boolean agregarDepartamento(Departamento o)
    {
        return dao.agregarDepartamento(o);
    }
    
    public boolean modificarDepartamento(Departamento o)
    {
        return dao.ModificarDepartamento(o);
    }
    
    public boolean EliminarDepartamento(Integer id)
    {
        return dao.EliminarDepartamento(id);
    }
    
    //metodo para llenar combo departamento
    public void llenarcomboDepartamento(JComboBox cbo){
        lista = dao.getAll();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
                for(Departamento obj: lista)
        {
                    modelo.addElement(obj);
        }
                //actualiza la informacion en el combo
                cbo.setModel(modelo);
    }
    
    public void seleccionarItemCbo(JComboBox cbo, Integer id){
        DefaultComboBoxModel modelo = (DefaultComboBoxModel)cbo.getModel();
        
        for(int i=0; i<modelo.getSize();i++){
            if(((Departamento)modelo.getElementAt(i)).getId_depa()==id){
                modelo.setSelectedItem(modelo.getElementAt(i));
            }
        }
    }

}
