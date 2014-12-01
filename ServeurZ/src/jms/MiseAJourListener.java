package jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.CustomJMSListener;
import basecode.FilesJMS;
import messages.MiseAJour;

/**
 *
 * @author Yvan
 */
public class MiseAJourListener extends CustomJMSListener {

    public MiseAJourListener() {
        super(FilesJMS.MISE_A_JOUR);
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            MiseAJour crea = (MiseAJour) mess.getObject();

            ObjectsCache.getInstance().put(crea.getObjet());

        } catch (JMSException ex) {
            Logger.getLogger(MiseAJourListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
