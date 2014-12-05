package basecode;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stocke une adresse IP de manière non statique dans le programme
 *
 * @author Yvan
 */
public class Adresse implements Serializable{

    /**
     * IP stockée
     */
    private InetAddress ip;

    /**
     * Crée une adresse nulle
     */
    public Adresse() {
        this.ip = null;
    }
    
    /**
     * demande si l'IP est remplie
     * @return vraie si remplie
     */
    public boolean isSet(){
        return this.ip != null;
    }

    /**
     * Crée une adresse à partir de la chaîne sous forme d'IP ou de lien
     *
     * @param ipText Adresse textuelle
     */
    public Adresse(String ipText) {
        super();
        this.setIp(ipText);
    }

    public Adresse(InetAddress add) {
        if (add instanceof Inet4Address) {
            this.ip = add;
        } else {
            this.ip = null;
        }
    }

    /**
     * Retourne l'InetAddress de l'IP stockée
     *
     * @return objet IP stockée
     */
    public InetAddress getInet() {
        return this.ip;
    }

    /**
     * Modifie l'ip stockée
     *
     * @param ipText Nouvelle IP textuelle
     */
    public final void setIp(String ipText) {
        try {
            this.ip = InetAddress.getByName(ipText);
        } catch (UnknownHostException ex) {
            this.ip = null;
        }
    }

    /**
     * Retourne l'IP textuelle insérable dans un lien réseau
     *
     * @return IP textuelle
     */
    public String getTextIp() {
        if (this.ip == null) {
            try {
                this.ip = InetAddress.getByName("localhost");
            } catch (UnknownHostException ex) {
                Logger.getLogger(Annuaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.ip.getHostAddress();
    }

    @Override
    public String toString() {
        return this.ip.getHostAddress();
    }
}
