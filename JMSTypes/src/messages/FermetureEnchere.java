package messages;

/**
 * Message signalant la fermeture volontaire de l'enchère par le créateur
 *
 * @author Yvan
 */
public class FermetureEnchere implements MessageJMSCustom {

    private static final long serialVersionUID = 64269489624964269L;
    private final int idBien;

    public FermetureEnchere(int idBien) {
        this.idBien = idBien;
    }

    public int getIdBien() {
        return idBien;
    }
}
