package clientele;


import basecode.Annuaire;
import basecode.FilesJMS;
import basecode.Lancement;
import basecode.TabAdresse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yvan
 */
public class Clientele extends Lancement {

    @Override
    public void onListenersLaunch() {
        CreationClientListener creationListener = new CreationClientListener();
        ConnexionListener connexionListener = new ConnexionListener();
        DemandeNotifListener demandeNotifListener = new DemandeNotifListener();
        FinEnchereListener finEnchereListener = new FinEnchereListener();
        LectureNotifListener lectureNotifListener = new LectureNotifListener();
        NotificationEnchereListener notificationEnchereListener = new NotificationEnchereListener();
        TableauBordListener tableauBordListener = new TableauBordListener();
    }
    
    @Override
    public void onParamsSet() {
        this.setTitle("Serveur Clientèle");
        try {
            this.setOpenMessage("Serveur de clientele lance sur l'adresse " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            this.setOpenMessage("Serveur de clientele lance");
        }
    }

    public static void main(String[] args) {
        if(args.length > 0) try {
            Annuaire.setIp(InetAddress.getByName(args[0]));
        } catch (UnknownHostException ex) {
            Logger.getLogger(Clientele.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Clientele().run();
    }

    @Override
    public void onFilesCompletion() {
        TabAdresse.getInstance()
                .ajouterFile(FilesJMS.CONNEXION)
                .ajouterFile(FilesJMS.CREATION_CLIENT)
                .ajouterFile(FilesJMS.DEMANDE_NOTIF)
                .ajouterFile(FilesJMS.FIN_ENCHERE)
                .ajouterFile(FilesJMS.LECTURE_NOTIFS)
                .ajouterFile(FilesJMS.NOTIFICATION)
                .ajouterFile(FilesJMS.NOTIFICATION_ENCHERE)
                .ajouterFile(FilesJMS.OPERATION)
                .ajouterFile(FilesJMS.REQ_CLIENT)
                .ajouterFile(FilesJMS.RETOUR_BANQUE)
                .ajouterFile(FilesJMS.RETOUR_DEMAND_NOTIF)
                .ajouterFile(FilesJMS.RETOUR_CONNEXION)
                .ajouterFile(FilesJMS.RETOUR_REQ_CLIENT)
                .ajouterFile(FilesJMS.RETOUR_TABLE_BORD)
                .ajouterFile(FilesJMS.TABLE_BORD);
    }
}
