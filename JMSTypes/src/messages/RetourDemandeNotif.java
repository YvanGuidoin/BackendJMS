package messages;

import java.util.ArrayList;

/**
 *
 * @author Yvan
 */
public class RetourDemandeNotif implements MessageJMSCustom {
    
    private static final long serialVersionUID = 486924892469L;
    private final ArrayList<Notification> notifs;

    public RetourDemandeNotif(ArrayList<Notification> notifs) {
        this.notifs = notifs;
    }

    public ArrayList<Notification> getNotifs() {
        return notifs;
    }
}
