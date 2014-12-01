package clientele;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.CustomJMSListener;
import basecode.FilesJMS;
import messages.Notification;

/**
 *
 * @author Yvan
 */
public class NotificationEnchereListener extends CustomJMSListener{
    
    public NotificationEnchereListener(){
        super(FilesJMS.NOTIFICATION_ENCHERE);
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            Notification notif = (Notification) mess.getObject();
            
            SqlRequester.getInstance().addNotification(notif);
            NotificationSender.getInstance().send(notif);
            
        } catch (JMSException ex) {
            Logger.getLogger(NotificationEnchereListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
