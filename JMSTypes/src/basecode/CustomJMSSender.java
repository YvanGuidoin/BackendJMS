package basecode;

import java.net.InetAddress;
import messages.MessageJMSCustom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.NamingException;
import org.exolab.jms.message.ObjectMessageImpl;

/**
 * Classe abstraite gérant la création d'un envoyeur sur une file JMS
 *
 * @author Yvan
 */
public class CustomJMSSender {

    /**
     * Connection au serveur JMS
     */
    private Connection conn;
    /**
     * File de destination
     */
    private Destination dest;
    /**
     * Session d'envoi
     */
    private Session session;
    /**
     * Producteur de message
     */
    private MessageProducer sender;

    /**
     * Initialise l'envoyeur pour une file JMS
     *
     * @param file File de destination
     */
    protected CustomJMSSender(FilesJMS file) {
        try {
            InetAddress adresseJMSFile = TabAdresse.getInstance().getInet(file);
            dest = (Destination) Connexion.getInstance(adresseJMSFile).getContext().lookup(file.getValue());
            conn = Connexion.getInstance(adresseJMSFile).getFactory().createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            sender = session.createProducer(dest);
            conn.start();
        } catch (JMSException | NamingException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Envoie un message sur la file
     *
     * @param cust message à envoyer
     */
    public void send(MessageJMSCustom cust) {
        try {
            ObjectMessageImpl mess = new ObjectMessageImpl();
            mess.setObject(cust);
            sender.send(mess);
        } catch (JMSException ex) {
            Logger.getLogger(CustomJMSSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Envoie un message avec un discriminant JMSType
     *
     * @param cust message à envoyer
     * @param pattern discriminant
     */
    public void send(MessageJMSCustom cust, String pattern) {
        try {
            ObjectMessageImpl mess = new ObjectMessageImpl();
            mess.setObject(cust);
            mess.setJMSType(pattern);
            sender.send(mess);
        } catch (JMSException ex) {
            Logger.getLogger(CustomJMSSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
