package basecode;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.naming.NamingException;
import messages.AnnuaireDemande;
import messages.AnnuaireInfos;

/**
 * Used to store an IP statically in the program (only static access)
 *
 * @author Yvan
 */
public class Annuaire {
    
    /**
     * Numéro de requête actuel
     */
    private static int numReq = 0;

    /**
     * IP stockée
     */
    private static InetAddress ip;

    /**
     * Return the InetAddress object
     *
     * @return IP as InetAddress
     */
    public static InetAddress getIp() {
        return ip;
    }

    /**
     * Modifie l'IP stockée
     *
     * @param ip nouvelle IP
     */
    public static void setIp(InetAddress ip) {
        Annuaire.ip = ip;
    }

    /**
     * Retourne une représentation textuelle de l'IP insérable dans une
     * référence réseau
     *
     * @return IP textuelle
     */
    public static String getTextIp() {
        if (ip == null) {
            try {
                ip = InetAddress.getByName("localhost");
            } catch (UnknownHostException ex) {
                Logger.getLogger(Annuaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Annuaire.ip.getHostAddress();
    }

    public static TreeMap<FilesJMS, Adresse> getAnnuaire() {
        try {
            InetAddress adresseJMSFile = Annuaire.getIp();
            Destination dest = (Destination) Connexion.getInstance(adresseJMSFile).getContext().lookup(FilesJMS.ANNUAIRE.getValue());
            Connection conn = Connexion.getInstance(adresseJMSFile).getFactory().createConnection();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            conn.start();

            TemporaryQueue queueRetour = session.createTemporaryQueue();
            MessageProducer sender = session.createProducer(dest);
            MessageConsumer receiver = session.createConsumer(queueRetour);
           
            ObjectMessage demande = session.createObjectMessage();
            demande.setObject(new AnnuaireDemande(InetAddress.getLocalHost().getHostAddress()));
            demande.setJMSReplyTo(queueRetour);
            demande.setJMSCorrelationID(Annuaire.numReq+"");
            sender.send(demande);

            ObjectMessage reponse = (ObjectMessage) receiver.receive();
            AnnuaireInfos infos = (AnnuaireInfos) reponse.getObject();
            TreeMap<FilesJMS, Adresse> tab = (TreeMap<FilesJMS, Adresse>) infos.getData();
            System.out.println("Annuaire recu");

            sender.close();
            session.close();

            return tab;
        } catch (JMSException | NamingException | UnknownHostException ex) {
            Logger.getLogger(Annuaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
