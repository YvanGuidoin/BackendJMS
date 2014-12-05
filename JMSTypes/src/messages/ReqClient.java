package messages;

/**
 * Message demandant un détail des informations du client
 *
 * @author Yvan
 */
public class ReqClient implements MessageJMSCustom {

    private static final long serialVersionUID = 649246982486924926L;
    private final int idClient;

    public ReqClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdClient() {
        return idClient;
    }

}
