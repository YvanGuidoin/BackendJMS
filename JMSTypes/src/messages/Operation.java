package messages;

/**
 * Message décrivant à la banque une nouvelle opération bancaire
 *
 * @author Yvan
 */
public class Operation implements MessageJMSCustom {

    private static final long serialVersionUID = 648984988424929L;
    private final String IBAN;
    private final double montant;
    private final Ecriture transaction;

    public Operation(String IBAN, double montant, Ecriture transaction) {
        this.IBAN = IBAN;
        this.montant = montant;
        this.transaction = transaction;
    }

    public String getIBAN() {
        return IBAN;
    }

    public double getMontant() {
        return montant;
    }

    public Ecriture getTransaction() {
        return transaction;
    }
}
