package interfaces;
import conexion.ConexionDB;
import modelo.Contacto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Creamos la implementación de la interface contactosInterface.
//
public class contactoImplement extends ConexionDB implements contactosInterface{


    @Override
    public boolean crear(Contacto cont){
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO contactos.contacto (nombre, apellido, telefono, correo_electronico, fecha_nacimiento) VALUES (?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cont.getNombre());
            ps.setString(2, cont.getApellido());
            ps.setString(3, cont.getTelefono());
            ps.setString(4, cont.getCorreo_electronico());
            ps.setString(5, cont.getFecha_nacimiento());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public boolean modificar(Contacto cont) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE contactos.contacto  SET nombre=?, apellido=?, telefono=?, correo_electronico=?, fecha_nacimiento=? WHERE id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cont.getNombre());
            ps.setString(2, cont.getApellido());
            ps.setString(3, cont.getTelefono());
            ps.setString(4, cont.getCorreo_electronico());
            ps.setString(5,  cont.getFecha_nacimiento());
            ps.setInt(6, cont.getId());
            ;
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public boolean eliminar(Contacto cont) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM contactos.contacto WHERE id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, cont.getId());
            ;
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }


    @Override
    public List<Contacto> listar() {
        List<Contacto> productos =new ArrayList<>();

        try(Statement stmt = getConexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contacto")){
            while (rs.next()){
                Contacto c = crearContacto(rs);
                productos.add(c);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return productos;
    }
    private Contacto crearContacto(ResultSet rs) throws SQLException{
        Contacto c = new Contacto();
        c.setId(rs.getInt("id"));
        c.setNombre(rs.getNString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setTelefono(rs.getString("telefono"));
        c.setCorreo_electronico(rs.getString("correo_electronico"));
        c.setFecha_nacimiento(rs.getString("fecha_nacimiento"));

        return c;
    }
}
