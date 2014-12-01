package clientele;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.CustomJMSListener;
import basecode.FilesJMS;
import messages.ConnexionRes;
import messages.ConnexionRetour;
import messages.ConnexionTentative;

/**
 *
 * @author Yvan
 */
public class ConnexionListener extends CustomJMSListener {

    public ConnexionListener() {
        super(FilesJMS.CONNEXION);
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            ConnexionTentative tentative = (ConnexionTentative) mess.getObject();
            int ret = SqlRequester.getInstance().testConnexion(tentative.getLogin(), tentative.getMdp());
            ConnexionRetour reponse;
            if (ret == -2) {
                reponse = new ConnexionRetour(ConnexionRes.ERREUR, 0);
            } else if (ret == -1) {
                reponse = new ConnexionRetour(ConnexionRes.ECHEC, 0);
            } else {
                reponse = new ConnexionRetour(ConnexionRes.REUSSIE, ret);
            }

            RetourConnexionSender.getInstance().send(reponse, tentative.getIdRequest() + "");

        } catch (JMSException ex) {
            Logger.getLogger(CreationClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
