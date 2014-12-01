package clientele;


import basecode.FilesJMS;
import basecode.Lancement;
import basecode.TabAdresse;

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
