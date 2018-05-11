package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Comentario;
import modelo.Pedido;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-11T14:26:44")
@StaticMetamodel(Juego.class)
public class Juego_ { 

    public static volatile SingularAttribute<Juego, String> descripcion;
    public static volatile SingularAttribute<Juego, String> nombreJuego;
    public static volatile SingularAttribute<Juego, String> consola;
    public static volatile SingularAttribute<Juego, Float> promedio;
    public static volatile SingularAttribute<Juego, String> empresaDesarrolladora;
    public static volatile SingularAttribute<Juego, String> imagen;
    public static volatile ListAttribute<Juego, Pedido> pedidoList;
    public static volatile SingularAttribute<Juego, String> linkVideo;
    public static volatile SingularAttribute<Juego, Float> precio;
    public static volatile ListAttribute<Juego, Comentario> comentarioList;
    public static volatile SingularAttribute<Juego, String> genero;
    public static volatile SingularAttribute<Juego, Integer> idJuego;
    public static volatile SingularAttribute<Juego, Integer> stock;

}