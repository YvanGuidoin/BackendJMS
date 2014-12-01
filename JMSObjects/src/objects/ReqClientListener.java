package objects;


import java.util.ArrayList;
import basecode.CustomJMSListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.FilesJMS;
import messages.DescriptionBien;
import messages.Enchere;
import messages.ReqClient;
import messages.ResultReqClient;

/**
 *
 * @author Yvan
 */
public class ReqClientListener extends CustomJMSListener {

    public ReqClientListener() {
        super(FilesJMS.REQ_CLIENT, "JMSType like '" + Biens.getCategorie().asDeterminant()+"%'");
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            ReqClient crea = (ReqClient) mess.getObject();

            ArrayList<DescriptionBien> biensDesc = new ArrayList<>();
            ArrayList<Enchere> encheresEnCours = new ArrayList<>();

            ArrayList<Integer> biensConcernes = SqlRequester.getInstance().getBiensByIDClient(crea.getIdClient());
            for (int idbien : biensConcernes) {
                DescriptionBien desc = SqlRequester.getInstance().getDescById(idbien);
                biensDesc.add(desc);
                if (SqlRequester.getInstance().getStatut(idbien).equals(StatutEnchere.ENCOURS)) {
                    encheresEnCours.addAll(SqlRequester.getInstance().getEncheresByBienClient(idbien, crea.getIdClient()));
                }
            }
            ResultReqClientSender.getInstance().send(new ResultReqClient(biensDesc, encheresEnCours), mess.getJMSType());

        } catch (JMSException ex) {
            Logger.getLogger(ReqClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
