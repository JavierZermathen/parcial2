package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//utilizando el driver de conexió, conecto la base de datos "contactos" con el proyecto.
public class ConexionDB {

    private static final String username = "root";
    private static final String password = "12345";
    private static final String URL = "jdbc:mysql://localhost:3306/contactos?serverTimezone=UTC";
    private static Connection con = null;


    public static Connection getConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, username,password);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
