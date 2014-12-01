package clientele;


import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class NotificationSender extends CustomJMSSender {

    private static final NotificationSender INSTANCE = new NotificationSender();

    private NotificationSender() {
        super(FilesJMS.NOTIFICATION);
    }

    public static NotificationSender getInstance() {
        return INSTANCE;
    }
}
