package clientele;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.Categories;
import basecode.CustomJMSListener;
import basecode.CustomJMSReceiver;
import basecode.FilesJMS;
import messages.DescriptionBien;
import messages.ReqClient;
import messages.ResultReqClient;
import messages.RetourTableauBord;
import messages.TableauBord;

/**
 *
 * @author Yvan
 */
public class TableauBordListener extends CustomJMSListener{
    
    public TableauBordListener() {
        super(FilesJMS.TABLE_BORD);
    }

    @Override
    public void onMessage(Message msg) {
        try{
            ObjectMessage mess = (ObjectMessage) msg;
            TableauBord req = (TableauBord) mess.getObject();
            
            ArrayList<DescriptionBien> biens = new ArrayList<>();
            if(req.isWithEnch()){
//                for(Categories cat : Categories.values()){
                    Categories cat = Categories.INFORMATIQUE;
                    String determinant = cat.toString()+mess.getJMSType();
                    ReqClientSender.getInstance().send(new ReqClient(req.getIdClient()), determinant);
                    ResultReqClient tabs = (ResultReqClient) CustomJMSReceiver.receive(FilesJMS.RETOUR_REQ_CLIENT, "JMSType = '" +determinant + "'");
                    biens.addAll(tabs.getBiens());
//                }
            }
                        
            RetourTableauBord reponse = new RetourTableauBord(
                    SqlRequester.getInstance().getLogin(req.getIdClient()),
                    SqlRequester.getInstance().getIBAN(req.getIdClient()),
                    SqlRequester.getInstance().getNom(req.getIdClient()),
                    SqlRequester.getInstance().getPrenom(req.getIdClient()),
                    SqlRequester.getInstance().getAdresse(req.getIdClient()),
                    biens);
            RetourTableauBordSender.getInstance().send(reponse, mess.getJMSType());
            
        } catch (JMSException ex) {
            Logger.getLogger(TableauBordListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
