package messages;

/**
 * Demande d'annuaire
 *
 * @author Yvan
 */
public class AnnuaireDemande implements MessageJMSCustom {
    
    private static final long serialVersionUID = 5146244692468244L;
    final private String identity;
    
    public AnnuaireDemande(String identity) {
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }

}
