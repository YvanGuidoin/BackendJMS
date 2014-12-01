package messages;

import java.sql.Timestamp;

/**
 * Message de notification à un client
 *
 * @author Yvan
 */
public class Notification implements MessageJMSCustom {
    final private int idClient;
    final private DescriptionBien bien;
    final private TypeNotification type;
    final private boolean lue;
    final private Timestamp date;

    public Notification(int idClient, DescriptionBien bien, TypeNotification type, boolean lue, Timestamp date) {
        this.idClient = idClient;
        this.bien = bien;
        this.type = type;
        this.lue = lue;
        this.date = date;
    }

    public int getIdClient() {
        return idClient;
    }

    public DescriptionBien getBien() {
        return bien;
    }

    public TypeNotification getType() {
        return type;
    }   

    public boolean isLue() {
        return lue;
    }

    public Timestamp getDate() {
        return date;
    }
    
}
