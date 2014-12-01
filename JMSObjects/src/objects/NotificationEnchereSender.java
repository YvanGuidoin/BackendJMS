package objects;


import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class NotificationEnchereSender extends CustomJMSSender {
    
    private static NotificationEnchereSender INSTANCE = new NotificationEnchereSender();
    
    private NotificationEnchereSender(){
        super(FilesJMS.NOTIFICATION_ENCHERE);
    }
    
    public static NotificationEnchereSender getInstance(){
        return INSTANCE;
    }
    
}
