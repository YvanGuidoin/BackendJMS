package basecode;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Classe abstraite permettant d'uniformiser la procédure de lancement des
 * serveurs
 *
 * @author Yvan
 */
public abstract class Lancement implements Runnable {

    private String title;
    private String firstMess;

    /**
     * Algorithme de lancement du serveur
     */
    public void run() {
        this.onParamsSet();
        ConsolePanel.launchConsole(this.title);

        if(Annuaire.getIp() == null) Annuaire.setIp(this.getAdresseAnnuaire());
        this.onFilesCompletion();


        while (!TabAdresse.getInstance().obtenirFiles());

        this.onThreadsLaunch();        
        this.onListenersLaunch();
    }

    /**
     * Evenement de lancement des listeners JMS
     */
    public abstract void onListenersLaunch();

    /**
     * Ajout des files nécessaires au programme dans le gestionnaire
     */
    public abstract void onFilesCompletion();

    /**
     * Evenement de lancement des threads particuliers à un serveur (optionnel)
     */
    public void onThreadsLaunch() {
    }

    /**
     * Evenement modifiant le titre du serveur
     */
    public void onParamsSet() {
        try {
            String add = Inet4Address.getLocalHost().getHostAddress();
            this.setTitle("Serveur " + add);
            this.setOpenMessage("Lancement serveur a l'adresse " + add + " avec un role inconnu");
        } catch (UnknownHostException ex) {
            this.setTitle("Serveur");
            this.setOpenMessage("Lancement serveur");
        }
    }

    /**
     * Modifie le titre
     *
     * @param title nouveau titre
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Modifie le message d'ouverture du serveur
     */
    public void setOpenMessage(String str) {
        this.firstMess = str;
    }

    /**
     * Crée un dialogue demandant une adresse IP valable pour le serveur JMS
     * d'annuaire
     *
     * @return Objet d'adresse IP
     */
    public static InetAddress getAdresseAnnuaire() {
        InetAddress ip;
        while (true) {
            try {
                String str = new CustomInputString("IP de la file 'Annuaire'", "localhost", "Entrez une IP").showDialog();
                ip = InetAddress.getByName(str);
                if (ip instanceof Inet4Address) {
                    break;
                }
            } catch (UnknownHostException ex) {
                System.out.println("IP non valide");
            }
        }
        return ip;
    }

}
