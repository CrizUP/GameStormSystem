package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Comentario;
import modelo.Pedido;
import modelo.Persona;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-05T10:33:00")
@StaticMetamodel(Cuenta.class)
public class Cuenta_ { 

    public static volatile ListAttribute<Cuenta, Comentario> comentarioList;
    public static volatile ListAttribute<Cuenta, Pedido> pedidoList;
    public static volatile SingularAttribute<Cuenta, String> usuario;
    public static volatile SingularAttribute<Cuenta, String> contrasena;
    public static volatile SingularAttribute<Cuenta, String> rol;
    public static volatile SingularAttribute<Cuenta, Persona> idPersona;

}