--Trigger
CREATE TRIGGER actualizarStock after INSERT on pedido
FOR each ROW
update juego set stock = stock- NEW.cantidadJuegos WHERE idJuego=New.idJuego;