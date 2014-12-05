package messages;

/**
 * Demande de Tableau de Bord
 * @author Yvan
 */
public class TableauBord implements MessageJMSCustom {
    
    private static final long serialVersionUID = 11314512365535423L;
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
