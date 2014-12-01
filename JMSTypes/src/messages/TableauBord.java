package messages;

/**
 * Demande de Tableau de Bord
 * @author Yvan
 */
public class TableauBord implements MessageJMSCustom {
    final private int idClient;
    final private boolean withEnch;

    public TableauBord(int idClient, boolean withEnch) {
        this.idClient = idClient;
        this.withEnch = withEnch;
    }

    public int getIdClient() {
        return idClient;
    }

    public boolean isWithEnch() {
        return withEnch;
    }
}
