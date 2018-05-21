package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cuenta;
import modelo.Juego;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-14T23:20:00")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Integer> cantidadJuegos;
    public static volatile SingularAttribute<Pedido, String> estatus;
    public static volatile SingularAttribute<Pedido, Date> fechaLimite;
    public static volatile SingularAttribute<Pedido, Cuenta> usuario;
    public static volatile SingularAttribute<Pedido, Juego> idJuego;
    public static volatile SingularAttribute<Pedido, Integer> idPedido;
    public static volatile SingularAttribute<Pedido, Date> fechaApartado;
    public static volatile SingularAttribute<Pedido, Float> precioTotal;
    public static volatile SingularAttribute<Pedido, Date> fechaPago;

}