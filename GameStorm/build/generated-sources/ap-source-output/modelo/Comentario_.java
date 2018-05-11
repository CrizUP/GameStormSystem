package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cuenta;
import modelo.Juego;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-11T14:26:44")
@StaticMetamodel(Comentario.class)
public class Comentario_ { 

    public static volatile SingularAttribute<Comentario, Integer> puntuacion;
    public static volatile SingularAttribute<Comentario, Cuenta> usuario;
    public static volatile SingularAttribute<Comentario, Juego> idJuego;
    public static volatile SingularAttribute<Comentario, String> comentario;
    public static volatile SingularAttribute<Comentario, Integer> idComentario;

}