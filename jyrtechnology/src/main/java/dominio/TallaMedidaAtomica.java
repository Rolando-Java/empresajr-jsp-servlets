package dominio;

public class TallaMedidaAtomica {

    private int idTallaMedidaAtomica;
    private MedidaAtomica medidaAtomica;
    private String medida;

    public TallaMedidaAtomica() {

    }

    public TallaMedidaAtomica(MedidaAtomica medidaAtomica, String medida) {
        this.medidaAtomica = medidaAtomica;
        this.medida = medida;
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

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @Override
    public String toString() {
        return "TallaMedidaAtomica{" + "idTallaMedidaAtomica=" + idTallaMedidaAtomica + ", medidaAtomica=" + medidaAtomica + ", medida=" + medida + '}';
    }

}
