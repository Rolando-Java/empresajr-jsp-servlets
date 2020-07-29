package dominio;

import java.math.BigDecimal;

public class TallaMedidaAtomica {

    private int idTallaMedidaAtomica;
    private MedidaAtomica medidaAtomica;
    private BigDecimal medida;

    public TallaMedidaAtomica() {

    }

    public int getIdTallaMedidaAtomica() {
        return idTallaMedidaAtomica;
    }

    public void setIdTallaMedidaAtomica(int idTallaMedidaAtomica) {
        this.idTallaMedidaAtomica = idTallaMedidaAtomica;
    }

    public MedidaAtomica getMedidaAtomica() {
        return medidaAtomica;
    }

    public void setMedidaAtomica(MedidaAtomica medidaAtomica) {
        this.medidaAtomica = medidaAtomica;
    }

    public BigDecimal getMedida() {
        return medida;
    }

    public void setMedida(BigDecimal medida) {
        this.medida = medida;
    }

    @Override
    public String toString() {
        return "TallaMedidaAtomica{" + "idTallaMedidaAtomica=" + idTallaMedidaAtomica + ", medidaAtomica=" + medidaAtomica + ", medida=" + medida + '}';
    }

}
