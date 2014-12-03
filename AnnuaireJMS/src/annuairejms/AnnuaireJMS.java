package annuairejms;

import java.util.ArrayList;
import basecode.Annuaire;
import basecode.ConsolePanel;
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
public class AnnuaireJMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //lancement console
        ConsolePanel.launchConsole("Annuaire JMS");
        System.out.println("Lancement du serveur d'annuaire des queues JMS");

        //demande adresse queue annuaire
        if(args.length > 0){
            String ip = args[0];
            try {
                Annuaire.setIp(InetAddress.getByName(ip));
            } catch (UnknownHostException ex) {
                Logger.getLogger(AnnuaireJMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else{
            Annuaire.setIp(Lancement.getAdresseAnnuaire());    
        }        
        System.out.println("File annuaire -> " + Annuaire.getTextIp());

        //initialise l'annuaire
        ArrayList<FilesJMS> list = new ArrayList<>();
        FilesJMS[] tab = FilesJMS.values();
        for (FilesJMS f : tab) {
            if (!f.equals(FilesJMS.ANNUAIRE)) {
                list.add(f);
            }
        }
        TabAdresse.getInstance().ajouterAllFiles(list);
        if(args.length > 0){
            TabAdresse.getInstance().renseignerAllFiles(args[0]);
        } else {
            TabAdresse.getInstance().renseignerFiles();
        }
        System.out.println(TabAdresse.getInstance().toString());

        //lance la connexion jms et le listener
        TabAdresse.getInstance().ajouterAnnuaire();

        AnnuaireListener listener = new AnnuaireListener();

    }

}
