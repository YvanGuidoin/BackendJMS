package messages;

/**
 * Message de retour de connexion
 *
 * @author Yvan
 */
public class ConnexionRetour implements MessageJMSCustom {

    private final ConnexionRes etat;
    private final int idClient;

    public ConnexionRetour(ConnexionRes etat, int idClient) {
        this.etat = etat;
        this.idClient = idClient;
    }

    public ConnexionRes getEtat() {
        return etat;
    }

    public int getIdClient() {
        return idClient;
    }

}
