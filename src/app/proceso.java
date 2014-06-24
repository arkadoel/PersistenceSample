package app;

import entidades.Persona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @date: 23-jun-2014 
 * @author https://github.com/Arkadoel
 */
public class proceso {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    /**
     * Constructor por defecto que inicializa los entity manager
     */
    public proceso(){
        //inicio del entity manager factory
        abrirConexion();
        
    }    
    
    /**
     * Abre la conexion a la base de datos
     */
    public void abrirConexion(){
         if(emf==null || emf.isOpen() == false){
            emf = Persistence.createEntityManagerFactory("ejemploPersistenciaPU");
         }
         if(em ==null || em.isOpen() == false){
             em = emf.createEntityManager();
         }        
    }
    
    /**
     * Finaliza la conexion
     */
    public void finalizar(){
        try{
            if(em.isOpen()){
                em.close();
            }
            if(emf.isOpen() ){
                emf.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Guarda los datos de una persona en la base de datos, si no existen
     * registros en la base de datos los inserta y en caso de que si existan
     * los actualiza
     * @param  Persona
     * @return true | false
     */
    public boolean GuardarActualizar(Persona p){        
        try{
            em.getTransaction().begin();
            List<Persona> personas = em.createNamedQuery("Persona.findByNombre",Persona.class)
                                        .setParameter("nombre", p.getNombre())
                                        .getResultList();
            if(personas.size()>0){
                /*Hay una persona con el nombre que elegimos
                    toca actualizar
                */
                Persona personaDB = personas.get(0);
                personaDB.setNombre( p.getNombre() );
                personaDB.setApellido( p.getApellido() );
                personaDB.setCp( p.getCp() );
                personaDB.setDireccion( p.getDireccion() );
                
                em.persist( personaDB );
            }
            else{
                //la persona no existe, toca insertar
                em.persist( p );
            }
            
            em.getTransaction().commit();
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    /**
     * Elimina los datos de una persona de la base de datos y lo hace buscandolo
     * por el campo ID de la base de datos.
     * @param Integer con el id de la persona en la base de datos
     * @return true | false
     */
    public boolean EliminarPersonaById(Integer _id){
        return true;
    }
    
    /**
     * Obtiene los datos de una persona de la base de datos, si el loginName
     * y el Codigo Postal son correctos devuelve un objeto Persona con los datos,
     * si es incorrecto devolvera NULL
     * @param _loginName
     * @param _cp
     * @return Objeto Persona | null
     */
    public Persona getPersona(String _loginName, String _cp){
        Persona p= null;
        
        return p;
    }
}
