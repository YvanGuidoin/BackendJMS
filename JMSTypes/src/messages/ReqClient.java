package messages;

/**
 * Message demandant un détail des informations du client
 *
 * @author Yvan
 */
public class ReqClient implements MessageJMSCustom {

    private final int idClient;

    public ReqClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdClient() {
        return idClient;
    }

}
