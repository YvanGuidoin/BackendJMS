package banque;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.FilesJMS;
import lib.Lancement;
import lib.TabAdresse;

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
        new Banque().run();
    }

    @Override
    public void onFilesCompletion() {
        TabAdresse.getInstance().ajouterFile(FilesJMS.OPERATION);
    }

}
