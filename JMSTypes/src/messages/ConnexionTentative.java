package messages;

/**
 * Message de tentative de connexion
 *
 * @author Yvan
 */
public class ConnexionTentative implements MessageJMSCustom {

    private static final long serialVersionUID = 6246894286942869L;
    private final String login;
    private final String mdp;
    private final int idRequest;

    public ConnexionTentative(String login, String mdp, int idRequest) {
        this.login = login;
        this.mdp = mdp;
        this.idRequest = idRequest;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public int getIdRequest() {
        return idRequest;
    }

}
