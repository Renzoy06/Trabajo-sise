
package com.edu.sise.dao;


public class DAOManager {
    
    //Creando nuestros atributos para Singleton
    private static final DAOManager instancia = new DAOManager();
    
    public static DAOManager getInstancia()
    {
        return instancia;
    }
    
    //fin de singleton
    
    //inicio factory
    private CarreraDao carreraDao;
    private DepartamentoDao departamentoDao;
    private ProvinciaDao provinciaDao;
    private TutorDao tutorDao;
    private ProfesorDao profesorDao;
    
    public CarreraDao getCarreraDao()
    {
        if(carreraDao == null)
            carreraDao = new CarreraDao();
        return carreraDao;
    }
    
    public DepartamentoDao getDepartamentoDao()
    {
        if(departamentoDao == null)
            departamentoDao = new DepartamentoDao();
        return departamentoDao;
    }
    
    public ProvinciaDao getProvinciaDao()
    {
        if(provinciaDao == null)
            provinciaDao = new ProvinciaDao();
        return provinciaDao;
    }
    
    public TutorDao getTutorDao()
    {
        if(tutorDao == null)
            tutorDao = new TutorDao();
        return tutorDao;
    }
    
    public ProfesorDao getProfesorDao()
    {
        if(profesorDao == null)
            profesorDao = new ProfesorDao();
        return profesorDao;
    }
    
}
