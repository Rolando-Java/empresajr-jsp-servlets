package datos;

import dominio.Pedido;
import dominio.Prenda;
import java.util.List;

public interface PedidoDao {
    
    List<Pedido> listarPedidos(int idUsuario);
    List<Pedido> listarPedidos(int idUsuario, String estadoPedido);
    List<Pedido> listarPedidos();
    List<Prenda> listarPrendas();
    void registrarPedido(Pedido pedido);
    
}
