package modelo;

import interfaces.contactoImplement;
import interfaces.contactosInterface;

public class controladorContacto {
    private vistaContacto vista = new vistaContacto();

    public controladorContacto(){

    }

    public void crear(Contacto cont){
        contactosInterface nuevoContacto = new contactoImplement();
        nuevoContacto.crear(cont);
    }

    public void modificar(Contacto cont){
        contactosInterface nuevoContacto = new contactoImplement();
        nuevoContacto.modificar(cont);
    }

    public void eliminar(Contacto cont){
        contactosInterface nuevoContacto = new contactoImplement();
        nuevoContacto.eliminar(cont);
    }


    }
