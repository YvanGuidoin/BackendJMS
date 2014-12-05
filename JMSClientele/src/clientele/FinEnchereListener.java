package clientele;


import java.sql.Timestamp;
import java.util.Calendar;
import basecode.CustomJMSListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.CustomJMSReceiver;
import basecode.FilesJMS;
import messages.Ecriture;
import messages.FinEnchere;
import messages.Notification;
import messages.Operation;
import messages.RetourBanque;
import messages.Transaction;
import messages.TypeNotification;

/**
 *
 * @author Yvan
 */
public class FinEnchereListener extends CustomJMSListener {

    private int idRequest = 0;
    
    public FinEnchereListener() {
        super(FilesJMS.FIN_ENCHERE);
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            FinEnchere crea = (FinEnchere) mess.getObject();

            String IBANcrediteur = SqlRequester.getInstance().getIBAN(crea.getIdVendeur());
            Operation credit = new Operation(IBANcrediteur, crea.getPrixFinal(), Ecriture.CREDIT);

            String IBANdebiteur = SqlRequester.getInstance().getIBAN(crea.getIdAcheteur());
            Operation debit = new Operation(IBANdebiteur, crea.getPrixFinal(), Ecriture.DEBIT);

            boolean reussite = false;
            Notification acheteur;
            Notification vendeur;
            idRequest++;
            String discriminant = "JMSType = '" +idRequest+"'";
            OperationSender.getInstance().send(debit, discriminant);
            RetourBanque res = (RetourBanque) CustomJMSReceiver.receive(FilesJMS.RETOUR_BANQUE, discriminant);
            RetourBanque res2;
            if(res.getEtat().equals(Transaction.REUSSIE)){
                idRequest++;
                discriminant = idRequest+"";
                OperationSender.getInstance().send(res, discriminant);
                res2 = (RetourBanque) CustomJMSReceiver.receive(FilesJMS.RETOUR_BANQUE, discriminant);
                if(res2.getEtat().equals(Transaction.REUSSIE)) reussite = true;
            }
            Calendar cal = Calendar.getInstance();
            Timestamp date_crea = new Timestamp(cal.getTimeInMillis());
            if(reussite){
                acheteur = new Notification(crea.getIdAcheteur(), crea.getBien(), TypeNotification.ACHAT, false,date_crea);
                vendeur = new Notification(crea.getIdVendeur(), crea.getBien(), TypeNotification.VENTE, false,date_crea);
            } else {
                acheteur = new Notification(crea.getIdAcheteur(), crea.getBien(), TypeNotification.ECHEC_ACHAT, false,date_crea);
                vendeur = new Notification(crea.getIdVendeur(), crea.getBien(), TypeNotification.ECHEC_VENTE, false,date_crea);
            }
            SqlRequester.getInstance().addNotification(acheteur);
            SqlRequester.getInstance().addNotification(vendeur);
            NotificationSender.getInstance().send(acheteur);
            NotificationSender.getInstance().send(vendeur);

        } catch (JMSException ex) {
            Logger.getLogger(CreationClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
