package basecode;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.SwingUtilities;

/**
 * Classe gérant la correspondance entre des files JMS et des serveurs multiples
 *
 * @author Yvan
 */
public class TabAdresse implements Serializable {

    /**
     * Instance du singleton
     */
    private static final TabAdresse INSTANCE = new TabAdresse();
    /**
     * Tableau d'association de données
     */
    private TreeMap<FilesJMS, Adresse> tab;

    /**
     * Initialise le singleton
     */
    private TabAdresse() {
        tab = new TreeMap<>();
    }

    /**
     * Ajoute l'annuaire aux adresses gérées
     */
    public void ajouterAnnuaire() {
        this.tab.put(FilesJMS.ANNUAIRE, new Adresse(Annuaire.getIp()));
    }

    /**
     * Ajouter une file à gérer
     *
     * @param file FilesJMS à ajouter
     * @return Instance du singleton mis à jour
     */
    public TabAdresse ajouterFile(FilesJMS file) {
        this.tab.put(file, new Adresse());
        return this;
    }

    /**
     * Ajoute une liste de files à gérer
     *
     * @param list liste de files à ajouter
     * @return Instance mise à jour
     */
    public TabAdresse ajouterAllFiles(Collection<FilesJMS> list) {
        for (FilesJMS f : list) {
            this.ajouterFile(f);
        }
        return this;
    }

    /**
     * Renvoie les adresses gérées non remplies
     *
     * @return Tableau des files sans adresse
     */
    private TreeMap<FilesJMS, Adresse> getNullAdresses() {
        TreeMap<FilesJMS, Adresse> list = new TreeMap<>();
        for (Map.Entry<FilesJMS, Adresse> entrySet : tab.entrySet()) {
            FilesJMS key = entrySet.getKey();
            Adresse value = entrySet.getValue();
            if (value.getInet() == null) {
                list.put(key, value);
            }
        }
        return list;
    }

    /**
     * Lance une boîte de dialogue demandant l'entrée d'adresses IP valides pour
     * les files gérées
     */
    public void renseignerFiles() {
        int erreurs = 1;
        while (erreurs > 0) {
            try {
                final TreeMap<FilesJMS, Adresse> aRemplir = this.getNullAdresses();
                erreurs = aRemplir.size();
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        new CustomInputAdresses(aRemplir).showDialog();
                    }
                });
                for (Map.Entry<FilesJMS, Adresse> aEntry : aRemplir.entrySet()) {
                    FilesJMS key = aEntry.getKey();
                    Adresse value = aEntry.getValue();
                    if (value.getInet() instanceof Inet4Address) {
                        tab.put(key, value);
                        erreurs--;
                    }
                }

            } catch (InterruptedException | InvocationTargetException ex) {
                erreurs = 1;
            }
        }
    }

    public boolean obtenirFiles() {
        TreeMap<FilesJMS, Adresse> annuaire = Annuaire.getAnnuaire();
        if (annuaire == null) {
            return false;
        }
        for (Map.Entry<FilesJMS, Adresse> entrySet : tab.entrySet()) {
            FilesJMS key = entrySet.getKey();
            Adresse value = entrySet.getValue();
            if (!annuaire.containsKey(key)) {
                return false;
            }
            tab.put(key, annuaire.get(key));
        }
        return true;
    }

    /**
     * Retourne l'IP textuelle d'une file
     *
     * @param file File demandée
     * @return Adresse IP textuelle
     */
    public String getTextIp(FilesJMS file) {
        return this.tab.get(file).getTextIp();
    }

    /**
     * Retourne l'InetAddress du serveur JMS d'une file
     *
     * @param file file à adresser
     * @return Adresse de la file
     */
    public InetAddress getInet(FilesJMS file) {
        return this.tab.get(file).getInet();
    }

    /**
     * Retourne le singleton
     *
     *     * @return Singleton
     */
    public static TabAdresse getInstance() {
        return INSTANCE;
    }

    public Map<FilesJMS, Adresse> getTab() {
        return (Map<FilesJMS, Adresse>) tab.clone();
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("TabAdresse :");
        for (int i = 0; i < tab.size(); i++) {
            strBuilder.append("\n");
            strBuilder.append(tab.keySet().toArray()[i].toString());
            strBuilder.append(" -> ");
            strBuilder.append(tab.values().toArray()[i].toString());
        }
        return strBuilder.toString();
    }
}
