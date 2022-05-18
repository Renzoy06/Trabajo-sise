
package com.edu.sise.logic;


import com.edu.sise.dao.DAOManager;
import com.edu.sise.dao.ProvinciaDao;
import com.edu.sise.entidades.Provincia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ProvinciaLogic {
    
    List<Provincia> lista;
    ProvinciaDao dao = DAOManager.getInstancia().getProvinciaDao();
    
    
    public DefaultTableModel getModelo()
    {
        //cargarlista();
        lista = dao.getAll();
        DefaultTableModel modelo =  new DefaultTableModel();
        //crear mis columnas
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("ID_DEPA");
        modelo.addColumn("DEPARTAMENTO");
        for(Provincia obj: lista)
        {
            Object data[] =
            {
                obj.getId_prov(),
                obj.getNombre(),
                obj.getId_depa(),
                obj.getDes_id_depa()
            };
            
            modelo.addRow(data);
        }          
        return  modelo;
    }

    public void imprimir(JTable tabla)
    {
        tabla.setModel(getModelo());
    }
    
    public boolean agregarProvincia(Provincia o)
    {
        return dao.agregarProvincia(o);
    }
    
    public boolean modificarProvincia(Provincia o)
    {
        return dao.ModificarProvincia(o);
    }
    
    public boolean EliminarProvincia(Integer id)
    {
        return dao.EliminarProvincia(id);
    }
    
     //metodo para llenar combo departamento
    public void llenarcomboProvincia(JComboBox cbo){
        lista = dao.getAll();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
                for(Provincia obj: lista)
        {
                    modelo.addElement(obj);
        }
                //actualiza la informacion en el combo
                cbo.setModel(modelo);
    }
    
    
    
    public void seleccionarItemCbo(JComboBox cbo, Integer id){
        DefaultComboBoxModel modelo = (DefaultComboBoxModel)cbo.getModel();
        
        for(int i=0; i<modelo.getSize();i++){
            if(((Provincia)modelo.getElementAt(i)).getId_depa()==id){
                modelo.setSelectedItem(modelo.getElementAt(i));
            }
        }
    }
}
