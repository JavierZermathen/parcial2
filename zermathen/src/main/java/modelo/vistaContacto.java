package modelo;

import java.util.List;

public class vistaContacto {
    public void verContacto(Contacto cont) {
        System.out.println("Datos del contacto: " + cont);
    }

    public void verContacto(List<Contacto> contactos){
        for(Contacto contacto : contactos){
            System.out.println("Datos del contacto: "+ contacto);
        }
    }
}
