package messages;

/**
 * Message signalant la fin d'une enchère avec le gagnant et le créateur au
 * serveur clientèle
 *
 * @author Yvan
 */
public class FinEnchere implements MessageJMSCustom {

    private final int idVendeur;
    private final int idAcheteur;
    private final DescriptionBien bien;
    private final double prixFinal;

    public FinEnchere(int idVendeur, int idAcheteur, double prixFinal, DescriptionBien bien) {
        this.idVendeur = idVendeur;
        this.idAcheteur = idAcheteur;
        this.prixFinal = prixFinal;
        this.bien = bien;
    }

    public int getIdVendeur() {
        return idVendeur;
    }

    public int getIdAcheteur() {
        return idAcheteur;
    }

    public double getPrixFinal() {
        return prixFinal;
    }

    public DescriptionBien getBien() {
        return bien;
    }

}
