package datos;

import dominio.Pedido;
import dominio.Produccion;
import java.util.List;

public interface ProduccionDao {
    
    List<Produccion> obtenerProduccion (int idPedido);
    void registrarProduccion(int idPedido, String estado, String fecha);
    List<Pedido> listarPedidos(String estadoPedido);
    List<Pedido> listarPedidos(String estadoPedido, String estadoProduccion);
    
}
