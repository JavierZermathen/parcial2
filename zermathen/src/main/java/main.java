import conexion.ConexionDB;
import interfaces.contactoImplement;
import interfaces.contactosInterface;
import modelo.Contacto;
import modelo.controladorContacto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws SQLException{
        try (Connection conn = ConexionDB.getConexion()){
            contactosInterface contactNuevo = new contactoImplement();
            contactNuevo.listar().forEach(c -> System.out.println(c.getId() + "|" + c.getNombre() + "|" + c.getApellido() + "|" + c.getCorreo_electronico() + "|" + c.getFecha_nacimiento()));


        } catch (SQLException e){
            System.err.println(e);
        }


        Contacto contacto = new Contacto("Enzo","Roh","33475125","mailviejo@mail","2011-08-10");

        //Controlador
        controladorContacto controller = new controladorContacto();

        //Creo un contacto a través del controlador
        controller.crear(contacto);

        //Modifico un contacto ingresado a la base de datos por el id
        contacto.setId(15);
        contacto.setNombre("Anibal");
        contacto.setApellido("Pachano");
        controller.modificar(contacto);

        //Elimino un contacto de la base de datos segun el id
        contacto.setId(20);
        controller.eliminar(contacto);
    }

}
