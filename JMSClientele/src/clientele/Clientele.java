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
        NotificationEnchereListener notificationEnchereListener = new NotificationEnchereListener();
        TableauBordListener tableauBordListener = new TableauBordListener();
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
                .ajouterFile(FilesJMS.NOTIFICATION)
                .ajouterFile(FilesJMS.NOTIFICATION_ENCHERE)
                .ajouterFile(FilesJMS.OPERATION)
                .ajouterFile(FilesJMS.REQ_CLIENT)
                .ajouterFile(FilesJMS.RETOUR_BANQUE)
                .ajouterFile(FilesJMS.RETOUR_CONNEXION)
                .ajouterFile(FilesJMS.RETOUR_REQ_CLIENT)
                .ajouterFile(FilesJMS.RETOUR_TABLE_BORD)
                .ajouterFile(FilesJMS.TABLE_BORD);
    }
}
