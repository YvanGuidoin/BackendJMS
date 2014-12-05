package objects;


import messages.StatutEnchere;
import java.util.ArrayList;
import basecode.CustomJMSListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.FilesJMS;
import messages.Reset;
import messages.MiseAJour;

/**
 *
 * @author Yvan
 */
public class ResetListener extends CustomJMSListener {

    public ResetListener() {
        super(FilesJMS.RESET, "JMSType = '"+ Biens.getCategorie().asDeterminant() + "'");
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            Reset crea = (Reset) mess.getObject();

            System.out.println("demande de reset");
            ArrayList<Integer> biensEnCours = SqlRequester.getInstance().getBiensIDbyStatut(StatutEnchere.ENCOURS);
            for (int idBien : biensEnCours) {
                MiseAJourSender.getInstance().send(new MiseAJour(SqlRequester.getInstance().getDescById(idBien)));
            }

        } catch (JMSException ex) {
            Logger.getLogger(ResetListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
