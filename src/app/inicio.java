package app;

import entidades.Persona;

/**
 * Aplicacion destinada a enseñar un funcionamiento basico del framework para 
 * manejo de bases de datos llamado Persistencia y denominado 
 * JPA (Java Persistence API)
 * 
 * Hay metodos que deben de ser completados 
 * 
 * @date: 23-jun-2014
 * @author https://github.com/Arkadoel
 */
public class inicio {
    
    /**
     * Inicio de la aplicacion
     * @param args 
     */
    public static void main(String args[]){
        try {
            escribir("Iniciando conexion");
            proceso p = new proceso();
            
            escribir("Creando datos de persona y guardando.");
            //creamos una persona y guardamos en la DB
            Persona pepe = new Persona();
            pepe.setNombre("Pepe");
            pepe.setApellido("Gonzalez");
            pepe.setCp("47000");
            pepe.setDireccion("C/de las angustias");            
            
            p.GuardarActualizar( pepe );
            
            //Prueba de obtener datos
            escribir("Obtener datos de la base de datos:");
            Persona datosPersona = p.getPersona("Paco", "47000");
            
            if(datosPersona!=null){
                escribir("\tLa persona vive en: " + datosPersona.getDireccion());
                escribirSeguido("Ahora se procedera a eliminar los datos de la base de datos");
                escribirSeguido("........................................");
                
                boolean seEliminaron = p.EliminarPersonaById( datosPersona.getIdPersona() );
                
                if(seEliminaron == true){
                    escribir("OK");
                }
            }
            else{
                escribir("Los datos de Nombre o Codigo Postal no son correctos");
            }
            
            escribir("Cerrando el programa");
            p.finalizar();
            escribir("Fin.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Escribe en la consola una linea y salta a la siguiente
     * @param _txt 
     */
    public static void escribir(String _txt){
        System.out.println(_txt);
    }
    
    /**
     * Escribe en la consola de comando pero sin saltar a la linea siguiente
     * @param _txt 
     */
    public static void escribirSeguido(String _txt){
        System.out.print(_txt);
    }
}
