package basecode;

import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.NamingException;
import messages.MessageJMSCustom;

/**
 * Classe abstraite gérant la création d'un lecteur de message unique sur une
 * file
 *
 * @author Yvan
 */
public class CustomJMSReceiver {

    /**
     * Connection au serveur JMS
     */
    private Connection conn;
    /**
     * Lien vers la file
     */
    private Destination dest;
    /**
     * Session active
     */
    private Session session;
    /**
     * Consommateur de la file
     */
    private MessageConsumer receiver;

    /**
     * Crée un receveur de message sur une file JMS
     *
     * @param file file à écouter
     */
    private CustomJMSReceiver(FilesJMS file) {
        try {
            InetAddress adresseJMSFile = TabAdresse.getInstance().getInet(file);
            dest = (Destination) Connexion.getInstance(adresseJMSFile).getContext().lookup(file.getValue());
            conn = Connexion.getInstance(adresseJMSFile).getFactory().createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            conn.start();
            receiver = session.createConsumer(dest);

        } catch (JMSException | NamingException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Crée un receveur de message sur une file JMS avec un discrimant PATTERN
     *
     * @param file file à écouter
     * @param pattern discrimant de sélection
     */
    private CustomJMSReceiver(FilesJMS file, String pattern) {
        try {
            InetAddress adresseJMSFile = TabAdresse.getInstance().getInet(file);
            dest = (Destination) Connexion.getInstance(adresseJMSFile).getContext().lookup(file.getValue());
            conn = Connexion.getInstance(adresseJMSFile).getFactory().createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            conn.start();
            receiver = session.createConsumer(dest, pattern);

        } catch (JMSException | NamingException exception) {
            exception.printStackTrace();
        }
    }

    protected void finalize() throws Throwable {
        receiver.close();
        conn.close();
        session.close();
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Crée un receveur de message unique et renvoie le message lu
     *
     * @param file file à écouter
     * @return message lu
     */
    public static MessageJMSCustom receive(FilesJMS file) {
        MessageJMSCustom m = null;
        CustomJMSReceiver cust = new CustomJMSReceiver(file);
        try {
            ObjectMessage message = (ObjectMessage) cust.receiver.receive();
            System.out.println("recu");
            m = (MessageJMSCustom) message.getObject();
        } catch (JMSException ex) {
            Logger.getLogger(CustomJMSReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    /**
     * Crée un receveur de message unique à discriminant et renvoie le message
     * lu
     *
     * @param file file à écouter
     * @param pattern discrimant de sélection
     * @return message lu
     */
    public static MessageJMSCustom receive(FilesJMS file, String pattern) {
        MessageJMSCustom m = null;
        CustomJMSReceiver cust = new CustomJMSReceiver(file, pattern);
        try {
            ObjectMessage message = (ObjectMessage) cust.receiver.receive();
            System.out.println("recu");
            m = (MessageJMSCustom) message.getObject();
        } catch (JMSException ex) {
            Logger.getLogger(CustomJMSReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

}
