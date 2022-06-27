package interfaces;

import modelo.Contacto;

import java.util.List;

//Esta clase del tipo interface contiene los métodos (luego se implementan en la clase contactoImplement)
public interface contactosInterface{
    boolean crear(Contacto cont);

    boolean modificar(Contacto cont);

    boolean eliminar(Contacto cont);

    List<Contacto> listar();
}
