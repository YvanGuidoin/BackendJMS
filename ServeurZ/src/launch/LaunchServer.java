package launch;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import jms.MiseAJourListener;
import jms.ObjectsCache;
import basecode.Adresse;
import basecode.Annuaire;
import basecode.CustomInputString;
import basecode.FilesJMS;
import basecode.Lancement;
import basecode.TabAdresse;
import java.net.UnknownHostException;
import jms.NotificationListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import webservice.ClientServlet;
import webservice.ConnexionServlet;
import webservice.EnchereServlet;
import webservice.ObjectsServlet;

/**
 *
 * @author Yvan
 */
public class LaunchServer extends Lancement {

    private static Adresse nodeJS = new Adresse();

    @Override
    public void onListenersLaunch() {
        MiseAJourListener maj = new MiseAJourListener();
        NotificationListener notif = new NotificationListener();
    }

    @Override
    public void onParamsSet() {
        this.setTitle("Serveur WS");
        this.setOpenMessage("Lancement du serveur WS");
    }

    @Override
    public void onFilesCompletion() {
        TabAdresse.getInstance()
                .ajouterFile(FilesJMS.CONNEXION)
                .ajouterFile(FilesJMS.CREATION_CLIENT)
                .ajouterFile(FilesJMS.CREATION_ENCHERES)
                .ajouterFile(FilesJMS.DEMANDE_NOTIF)
                .ajouterFile(FilesJMS.ENCHERES)
                .ajouterFile(FilesJMS.FERMETURE_ENCHERE)
                .ajouterFile(FilesJMS.LECTURE_NOTIFS)
                .ajouterFile(FilesJMS.MISE_A_JOUR)
                .ajouterFile(FilesJMS.NOTIFICATION)
                .ajouterFile(FilesJMS.RESET)
                .ajouterFile(FilesJMS.RETOUR_CONNEXION)
                .ajouterFile(FilesJMS.RETOUR_TABLE_BORD)
                .ajouterFile(FilesJMS.TABLE_BORD);
    }

    @Override
    public void onThreadsLaunch() {
        try {
            if (!nodeJS.isSet()) {
                InetAddress add;
                do {
                    String ipNode = new CustomInputString("IP du serveur nodeJS", "localhost", "Entrez une IP").showDialog();
                    add = InetAddress.getByName(ipNode);
                } while (!(add instanceof Inet4Address));
                nodeJS = new Adresse(add);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(LaunchServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //reset cache
        ObjectsCache.getInstance().init();

        //lancement du serveur http
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //lancement Webservices
                    Server server = new Server(8080);
                    ServletHandler handler = new ServletHandler();
                    handler.addServletWithMapping(ClientServlet.class, "/client/*");
                    handler.addServletWithMapping(ConnexionServlet.class, "/connexion");
                    handler.addServletWithMapping(ObjectsServlet.class, "/objet/*");
                    handler.addServletWithMapping(EnchereServlet.class, "/enchere/*");
                    server.setHandler(handler);
                    server.start();
                    server.join();
                } catch (Exception ex) {
                    Logger.getLogger(LaunchServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                Annuaire.setIp(InetAddress.getByName(args[0]));
                LaunchServer.setNodeJS(new Adresse(args[1]));
            } catch (UnknownHostException ex) {
                Logger.getLogger(LaunchServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        new LaunchServer().run();
    }

    public static Adresse getNodeJS() {
        if (nodeJS == null) {
            nodeJS = new Adresse("localhost");
        }
        return nodeJS;
    }

    public static void setNodeJS(Adresse nodeJS) {
        LaunchServer.nodeJS = nodeJS;
    }

}
