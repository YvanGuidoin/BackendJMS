package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class DemandeNotifSender extends CustomJMSSender {
    private static DemandeNotifSender INSTANCE = new DemandeNotifSender();
    
    private DemandeNotifSender(){
        super(FilesJMS.DEMANDE_NOTIF);
    }
    
    public static DemandeNotifSender getInstance(){
        return INSTANCE;
    }
}
