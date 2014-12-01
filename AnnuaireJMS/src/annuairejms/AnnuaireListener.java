package annuairejms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import basecode.CustomJMSListener;
import basecode.FilesJMS;
import basecode.TabAdresse;
import messages.AnnuaireDemande;
import messages.AnnuaireInfos;

/**
 *
 * @author Yvan
 */
public class AnnuaireListener extends CustomJMSListener {
    
    private MessageProducer producteur;
    
    public AnnuaireListener() {
        super(FilesJMS.ANNUAIRE);
        try {
            this.producteur = session.createProducer(null);
            this.producteur.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (JMSException ex) {
            System.out.println("Erreur de lancement de l'AnnuaireListener");
        }
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            AnnuaireDemande contenu = (AnnuaireDemande) mess.getObject();

            System.out.println(contenu.getIdentity() + " a demande l'annuaire");          

            ObjectMessage reponse = session.createObjectMessage();
            reponse.setObject(new AnnuaireInfos(TabAdresse.getInstance().getTab()));
            reponse.setJMSCorrelationID(mess.getJMSCorrelationID());
            producteur.send(mess.getJMSReplyTo(), reponse);

            System.out.println("Annuaire envoye a " + contenu.getIdentity());

        } catch (JMSException ex) {
            Logger.getLogger(AnnuaireListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.producteur.close();
        super.finalize();
    }
}
