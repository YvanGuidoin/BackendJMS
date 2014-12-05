package clientele;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class RetourDemandeNotifSender extends CustomJMSSender {
    private static RetourDemandeNotifSender INSTANCE = new RetourDemandeNotifSender();
    
    private RetourDemandeNotifSender(){
        super(FilesJMS.RETOUR_DEMAND_NOTIF);
    }
    
    public static RetourDemandeNotifSender getInstance(){
        return INSTANCE;
    }
}
