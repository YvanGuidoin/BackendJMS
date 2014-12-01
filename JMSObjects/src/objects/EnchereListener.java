package objects;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.CustomJMSListener;
import basecode.FilesJMS;
import messages.Enchere;
import messages.MiseAJour;
import messages.Notification;
import messages.TypeNotification;

/**
 *
 * @author Yvan
 */
public class EnchereListener extends CustomJMSListener {

    public EnchereListener() {
        super(FilesJMS.ENCHERES, "JMSType = '"+ Biens.getCategorie().asDeterminant() + "'");
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            Enchere crea = (Enchere) mess.getObject();

            SqlRequester.getInstance().addEnchere(crea.getMontant(), crea.getIdBien(), crea.getIdClient());

            Double increment = SqlRequester.getInstance().getIncremental(crea.getIdBien());
            Double newMontant = 0.0;
            ArrayList<Enchere> list = SqlRequester.getInstance().getEncheresByBien(crea.getIdBien());

            Enchere max = list.get(0);
            for (Enchere a : list) {
                if (a.getMontant() > max.getMontant()) {
                    max = a;
                }
            }

            list.remove(max);
            if (list.size() < 1) {
                newMontant = max.getMontant();
            } else {
                Enchere max2 = list.get(0);
                for (Enchere a : list) {
                    if (a.getMontant() > max2.getMontant()) {
                        max2 = a;
                    }
                }
                if (max2.getMontant() + increment >= max.getMontant()) {
                    newMontant = max.getMontant();
                } else {
                    newMontant = max2.getMontant() + increment;
                }
            }
            //maj
            SqlRequester.getInstance().updateMontantActuel(crea.getIdBien(), newMontant);
            SqlRequester.getInstance().updateGagnantActuel(crea.getIdBien(), max.getIdClient());

            //envoi maj
            MiseAJourSender.getInstance().send(new MiseAJour(SqlRequester.getInstance().getDescById(crea.getIdBien())));
            //création notifs
            list.add(max);
            ArrayList<Notification> notifs = new ArrayList<>();
            Calendar cal = Calendar.getInstance();
            Timestamp date_crea = new Timestamp(cal.getTimeInMillis());
            for(Enchere e : list){
                Notification notif = new Notification(
                        e.getIdClient(),
                        SqlRequester.getInstance().getDescById(crea.getIdBien()),
                        TypeNotification.ENCHERE,
                        false,
                        date_crea);
                notifs.add(notif);
            }
            //envoi notifs
            for(Notification n : notifs){
                NotificationEnchereSender.getInstance().send(n);
            }

        } catch (JMSException ex) {
            Logger.getLogger(EnchereListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
