package datos;

import dominio.Pedido;
import java.util.List;

public interface PedidoDao {
    
    List<Pedido> listarPedidos(int idUsuario);
    List<Pedido> listarPedidos(int idUsuario, String estadoPedido);
    
}
