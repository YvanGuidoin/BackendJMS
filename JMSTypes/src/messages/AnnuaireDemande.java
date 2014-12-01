package messages;

/**
 * Demande d'annuaire
 *
 * @author Yvan
 */
public class AnnuaireDemande implements MessageJMSCustom {

    final private String identity;

    public AnnuaireDemande(String identity) {
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }

}
