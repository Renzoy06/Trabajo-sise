
package com.edu.sise.logic;


import com.edu.sise.dao.CarreraDao;
import com.edu.sise.dao.DAOManager;
import com.edu.sise.entidades.Carrera;
import com.edu.sise.entidades.Profesor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class CarreraLogic {
    
    List<Carrera> lista;
    CarreraDao dao = DAOManager.getInstancia().getCarreraDao();
    
    public DefaultTableModel getModelo()
    {
        //cargarlista();
        lista = dao.getAll();
        DefaultTableModel modelo =  new DefaultTableModel();
        //crear mis columnas
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        for(Carrera obj: lista)
        {
            Object data[] =
            {
                obj.getId_carrera(),
                obj.getNombre(),
            };
            
            modelo.addRow(data);
        }          
        return  modelo;
    }
    
    public DefaultTableModel getModelo(String nombre)
    {
        //cargarlista();
        lista = dao.getSearch(nombre);
        DefaultTableModel modelo =  new DefaultTableModel();
        //crear mis columnas
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        for(Carrera obj: lista)
        {
            Object data[] =
            {
                obj.getId_carrera(),
                obj.getNombre(),
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
    
    public boolean agregarCarrera(Carrera o)
    {
        return dao.agregarCarrera(o);
    }
    
    public boolean modificarCarrera(Carrera o)
    {
        return dao.ModificarCarrera(o);
    }
    
    public boolean EliminarCarrera(Integer id)
    {
        return dao.EliminarCarrera(id);
    }
    
    public void generarReporte()
    {
        JasperReport reporte;
        String ruta ="C:\\Reportes_1129_Sise\\rpt_carreras_v1.jasper";
        //mis parametros
        Map<String,Object> parametros = new HashMap<String,Object>();
        
        parametros.put("empresa", "INSTITUTO SISE");
        
        try {
                //cargar el reporte en memoria
            reporte = (JasperReport)JRLoader.loadObjectFromFile(ruta);
            JasperPrint print = JasperFillManager.fillReport(reporte,parametros,
                    new JRBeanCollectionDataSource(dao.getAll()));
            JasperViewer viewer = new JasperViewer(print,false);
            viewer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            viewer.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
        
    }
    
    public void llenarcomboCarrera(JComboBox cbo){
        lista = dao.getAll();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
                for(Carrera obj: lista)
        {
                    modelo.addElement(obj);
        }
                //actualiza la informacion en el combo
                cbo.setModel(modelo);
    }
    
    
    
    public void seleccionarItemCbo(JComboBox cbo, Integer id){
        DefaultComboBoxModel modelo = (DefaultComboBoxModel)cbo.getModel();
        
        for(int i=0; i<modelo.getSize();i++){
            if(((Profesor)modelo.getElementAt(i)).getId_carrera()==id){
                modelo.setSelectedItem(modelo.getElementAt(i));
            }
        }
    }
    
    
}
