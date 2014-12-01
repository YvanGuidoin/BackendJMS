package messages;

/**
 * Retour de l'état de la transaction bancaire
 *
 * @author Yvan
 */
public class RetourBanque implements MessageJMSCustom {

    private final String IBAN;
    private final Transaction etat;

    public RetourBanque(String IBAN, Transaction etat) {
        this.IBAN = IBAN;
        this.etat = etat;
    }

    public String getIBAN() {
        return IBAN;
    }

    public Transaction getEtat() {
        return etat;
    }
}
