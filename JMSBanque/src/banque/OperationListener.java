package banque;


import basecode.CustomJMSListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.FilesJMS;
import messages.Ecriture;
import messages.Operation;
import messages.RetourBanque;
import messages.Transaction;

/**
 *
 * @author Yvan
 */
public class OperationListener extends CustomJMSListener {

    public OperationListener() {
        super(FilesJMS.OPERATION);
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            Operation ope = (Operation) mess.getObject();

            String IBAN = ope.getIBAN();
            RetourBanque retourBanque;

            if (SqlRequester.getInstance().existIBAN(IBAN)) {
                double montant = (ope.getTransaction().equals(Ecriture.CREDIT)) ? ope.getMontant() : -ope.getMontant();
                double decouvertAutorise = SqlRequester.getInstance().getDecouvertAutorise(IBAN);
                double solde = SqlRequester.getInstance().getSolde(IBAN);

                if (solde + montant >= decouvertAutorise) {
                    SqlRequester.getInstance().addSolde(ope.getIBAN(), montant);
                    System.out.println("Opération sur compte : " + ope.getIBAN() + " value : " + montant);
                    retourBanque = new RetourBanque(IBAN, Transaction.REUSSIE);
                } else {
                    retourBanque = new RetourBanque(IBAN, Transaction.ECHEC);
                }
            } else {
                retourBanque = new RetourBanque(IBAN, Transaction.ECHEC);
            }
            ResultOperationSender.getInstance().send(retourBanque, mess.getJMSType());

        } catch (JMSException ex) {
            Logger.getLogger(OperationListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
