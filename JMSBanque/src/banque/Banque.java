package banque;

import basecode.Annuaire;
import java.net.InetAddress;
import java.net.UnknownHostException;
import basecode.FilesJMS;
import basecode.Lancement;
import basecode.TabAdresse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yvan
 */
public class Banque extends Lancement {

    @Override
    public void onListenersLaunch() {
        OperationListener listener = new OperationListener();
    }

    @Override
    public void onParamsSet() {
        this.setTitle("Serveur Banque");
        try {
            this.setOpenMessage("Serveur de banque lance sur l'adresse " + InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            this.setOpenMessage("Serveur de banque lance");
        }
    }

    public static void main(String[] args) {
        if(args.length > 0) try {
            Annuaire.setIp(InetAddress.getByName(args[0]));
        } catch (UnknownHostException ex) {
            Logger.getLogger(Banque.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Banque().run();
    }

    @Override
    public void onFilesCompletion() {
        TabAdresse.getInstance()
                .ajouterFile(FilesJMS.OPERATION)
                .ajouterFile(FilesJMS.RETOUR_BANQUE);
    }

}
