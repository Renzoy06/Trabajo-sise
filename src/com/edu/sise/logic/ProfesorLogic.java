
package com.edu.sise.logic;


import com.edu.sise.dao.DAOManager;
import com.edu.sise.dao.ProfesorDao;
import com.edu.sise.entidades.Profesor;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ProfesorLogic {
    
    List<Profesor> lista;
    ProfesorDao dao = DAOManager.getInstancia().getProfesorDao();
    
    public DefaultTableModel getModelo()
    {
        //cargarlista();
        //lista = new ProfesorDao().getAll();
        lista = dao.getAll();
        DefaultTableModel modelo =  new DefaultTableModel();
        //crear mis columnas
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PAPELLIDO");
        modelo.addColumn("SAPELLIDO");
        modelo.addColumn("FNACIMIENTO");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("ID_CARRERA");
        modelo.addColumn("ID_PROV");
        for(Profesor obj: lista)
        {
            Object data[] =
            {
                obj.getId_profe(),
                obj.getDni(),
                obj.getNombre(),
                obj.getPapellido(),
                obj.getSapellido(),
                obj.getFnacimiento(),
                obj.getTelefono(),
                obj.getId_carrera(),
                obj.getId_prov()            
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
        //crear mis columnas
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("PAPELLIDO");
        modelo.addColumn("SAPELLIDO");
        modelo.addColumn("FNACIMIENTO");
        modelo.addColumn("TELEFONO");
        modelo.addColumn("ID_CARRERA");
        modelo.addColumn("ID_PROV");
        for(Profesor obj: lista)
        {
            Object data[] =
            {
                obj.getId_profe(),
                obj.getDni(),
                obj.getNombre(),
                obj.getPapellido(),
                obj.getSapellido(),
                obj.getFnacimiento(),
                obj.getTelefono(),
                obj.getId_carrera(),
                obj.getId_prov()            
            };
            
            modelo.addRow(data);
        }          
        return  modelo;
    }

    public void imprimir(JTable tabla)
    {
        tabla.setModel(getModelo());
    }
    
    public boolean agregarProfesor(Profesor o)
    {
        return  dao.agregarProfesor(o);
    }
    
    public boolean modificarProfesor(Profesor o)
    {
        return  dao.ModificarProfesor(o);
    }
    
    public boolean EliminarProfesor(Integer id)
    {
        return  dao.EliminarProfesor(id);
    }
    

    public void imprimir(JTable tabla, String nombre)
    {
        tabla.setModel(getModelo(nombre));
    }
    
    
    
    //vamos a crear un metodo para la carga masiva
    public int  cargaMasiva(String rutaArchivo)
    {
        int registros_afectados = 0;
        try {
            FileInputStream archivo = new FileInputStream(rutaArchivo);
            DataInputStream entrada = new DataInputStream(archivo);
            BufferedReader buffer =  new BufferedReader(new InputStreamReader(entrada));
            String linea;
            List<Profesor> ListaCargaMasiva = new ArrayList<>();
            while((linea=buffer.readLine())!=null)
            {
                System.out.println(linea);
                String[] campos = linea.split("\\|");
                ListaCargaMasiva.add(new Profesor(
                        -1,
                        campos[0], //DNI
                        campos[1], //NOMBRE
                        campos[2], //p apellido
                        campos[3], //s apellido
                        LocalDate.parse(campos[4]), //nacimiento
                        campos[5], //telefono
                        Integer.parseInt(campos[6]), //id carrera
                        Integer.parseInt(campos[7]) //id prov
                        
                        
                ));
            }
            //cerrar la entrada
            entrada.close();
            
            int[] resultados = new ProfesorDao().cargaMasiva(ListaCargaMasiva);
            
            for (int i = 0; i < resultados.length; i++) {
                registros_afectados += resultados[i];
            }
                    
            //agregar la logica para interactuar con la base de datos   
        } catch (Exception ex) {
            System.out.print("Error carga Masiva" + ex.getMessage());
        }
        return registros_afectados;
    }
}
