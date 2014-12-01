package messages;

/**
 * Message signalant la fermeture volontaire de l'enchère par le créateur
 *
 * @author Yvan
 */
public class FermetureEnchere implements MessageJMSCustom {

    private final int idBien;

    public FermetureEnchere(int idBien) {
        this.idBien = idBien;
    }

    public int getIdBien() {
        return idBien;
    }
}
