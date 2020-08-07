package datos;

import dominio.FichaTecnica;

public interface FichaTecnicaDao {
    
    void generarFichaTecnica(FichaTecnica fichaTecnica);
    int ingresarFichaTecnica(int idPedido, FichaTecnica fichaTecnica);
    void registrarDetalleFichaTecnica(FichaTecnica fichaTecnica);
    
}
