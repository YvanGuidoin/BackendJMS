package messages;

/**
 * Message de création de client
 *
 * @author Yvan
 */
public class CreationClient implements MessageJMSCustom {

    private final String login;
    private final String mdp;
    private final String IBAN;
    private final String nom;
    private final String prenom;
    private final String adresse;

    public CreationClient(String login, String mdp, String IBAN, String nom, String prenom, String adresse) {
        this.login = login;
        this.mdp = mdp;
        this.IBAN = IBAN;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

}
