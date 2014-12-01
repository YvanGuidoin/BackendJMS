package basecode;

import java.net.InetAddress;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.naming.NamingException;

/**
 * Classe abstraite gérant l'initialisation d'un listener sur une file JMS
 *
 * @author Yvan
 */
public abstract class CustomJMSListener implements MessageListener {

    /**
     * Connection pour la file
     */
    protected Connection conn;
    /**
     * Lien vers la file
     */
    private Destination dest;
    /**
     * Session active
     */
    protected Session session;
    /**
     * Consommateur de la file
     */
    private MessageConsumer receiver;

    /**
     * Crée un listener sur une file de nom DESTNAME
     *
     * @param file file
     */
    public CustomJMSListener(FilesJMS file) {
        try {
            InetAddress adresseJMSFile = TabAdresse.getInstance().getInet(file);
            dest = (Destination) Connexion.getInstance(adresseJMSFile).getContext().lookup(file.getValue());
            conn = Connexion.getInstance(adresseJMSFile).getFactory().createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            receiver = session.createConsumer(dest);
            receiver.setMessageListener(this);
            conn.start();
        } catch (JMSException | NamingException exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Crée un listener sur une file de nom DESTNAME
     *
     * @param file file
     */
    public CustomJMSListener(FilesJMS file, String pattern) {
        try {
            InetAddress adresseJMSFile = TabAdresse.getInstance().getInet(file);
            dest = (Destination) Connexion.getInstance(adresseJMSFile).getContext().lookup(file.getValue());
            conn = Connexion.getInstance(adresseJMSFile).getFactory().createConnection();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            receiver = session.createConsumer(dest, pattern);
            receiver.setMessageListener(this);
            conn.start();
        } catch (JMSException | NamingException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        receiver.close();
        conn.close();
        session.close();
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Actions à la consommation d'un message
     *
     * @param msg Message consommé
     */
    @Override
    public abstract void onMessage(Message msg);

}
