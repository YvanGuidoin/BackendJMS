package clientele;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.CustomJMSListener;
import basecode.FilesJMS;
import messages.DemandeNotif;
import messages.Notification;
import messages.RetourDemandeNotif;

/**
 *
 * @author Yvan
 */
public class DemandeNotifListener extends CustomJMSListener {
    
    public DemandeNotifListener(){
        super(FilesJMS.DEMANDE_NOTIF);
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            DemandeNotif demande = (DemandeNotif) mess.getObject();
            
            ArrayList<Notification> notifs = new ArrayList<>();
            if(demande.isOnlyNonLue()){
                notifs.addAll(SqlRequester.getInstance().getNotificationsNonLuesByClient(demande.getIdClient()));
            } else {
                notifs.addAll(SqlRequester.getInstance().getNotificationsByClientDepuis(demande.getIdClient(), demande.getTime()));
            }
            RetourDemandeNotifSender.getInstance().send(new RetourDemandeNotif(notifs), mess.getJMSType());
        } catch (JMSException ex) {
            Logger.getLogger(DemandeNotifListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
