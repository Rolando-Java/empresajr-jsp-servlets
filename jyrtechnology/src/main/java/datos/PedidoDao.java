package datos;

import dominio.Correo;
import dominio.Pedido;
import dominio.Prenda;
import dominio.Usuario;
import java.util.List;

public interface PedidoDao {
    
    List<Pedido> listarPedidos(int idUsuario);
    List<Pedido> listarPedidos(int idUsuario, String estadoPedido);
    List<Pedido> listarPedidos(String estadoPedido);
    List<Prenda> listarPrendas();
    void registrarPedido(Pedido pedido);
    void modificarEstadoPedido(int idPedido, String estadoPedido);
    Correo generarEmailSolicitudPedido(Usuario usuario, String mensaje);
    
}
