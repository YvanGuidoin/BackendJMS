package clientele;


import basecode.CustomJMSListener;
import messages.CreationClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class CreationClientListener extends CustomJMSListener {

    public CreationClientListener() {
        super(FilesJMS.CREATION_CLIENT);
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            CreationClient crea = (CreationClient) mess.getObject();
            SqlRequester.getInstance().addCompte(
                    crea.getLogin(),
                    crea.getMdp(),
                    crea.getIBAN(),
                    crea.getNom(),
                    crea.getPrenom(),
                    crea.getAdresse());

        } catch (JMSException ex) {
            Logger.getLogger(CreationClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
