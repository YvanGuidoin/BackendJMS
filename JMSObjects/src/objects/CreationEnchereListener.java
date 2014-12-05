package objects;


import basecode.CustomJMSListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.FilesJMS;
import messages.CreationEnchere;
import messages.MiseAJour;

/**
 *
 * @author Yvan
 */
public class CreationEnchereListener extends CustomJMSListener {

    public CreationEnchereListener() {
        super(FilesJMS.CREATION_ENCHERES, "JMSType = '"+ Biens.getCategorie().asDeterminant() + "'");
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            CreationEnchere crea = (CreationEnchere) mess.getObject();

            int idCreated = SqlRequester.getInstance().addBien(
                    crea.getNom(),
                    crea.getIdCreator(),
                    crea.getDescription(),
                    crea.getUrl(),
                    crea.getDate_depart(),
                    crea.getDuree(),
                    crea.getPrix_depart(),
                    crea.getPrix_depart(),
                    crea.getIncrement(),
                    crea.getQuantite(),
                    -1,
                    StatutEnchere.ENCOURS);
            if (idCreated >= 0) {
                MiseAJourSender.getInstance().send(new MiseAJour(SqlRequester.getInstance().getDescById(idCreated)));
            }

        } catch (JMSException ex) {
            Logger.getLogger(CreationEnchereListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
