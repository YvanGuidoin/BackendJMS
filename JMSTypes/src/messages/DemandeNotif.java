package messages;

import java.sql.Timestamp;

/**
 *
 * @author Yvan
 */
public class DemandeNotif implements MessageJMSCustom {
    final private int idClient;
    final private Timestamp time;
    final private boolean onlyNonLue;

    public DemandeNotif(int idClient, Timestamp time, boolean onlyNonLue) {
        this.idClient = idClient;
        this.time = time;
        this.onlyNonLue = onlyNonLue;
    }
    
    public int getIdClient() {
        return idClient;
    }

    public Timestamp getTime() {
        return time;
    }

    public boolean isOnlyNonLue() {
        return onlyNonLue;
    }
}
