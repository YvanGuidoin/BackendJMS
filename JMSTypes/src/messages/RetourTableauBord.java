package messages;

import java.util.ArrayList;

/**
 * Stocke les informations d'un compte utilisateur
 * @author Yvan
 */
public class RetourTableauBord implements MessageJMSCustom {
    
    private final String login;
    private final String IBAN;
    private final String nom;
    private final String prenom;
    private final String adresse;
    private final ArrayList<DescriptionBien> biens;
    private final ArrayList<Enchere> encheresEnCours;

    public RetourTableauBord(String login, String IBAN, String nom, String prenom, String adresse, ArrayList<DescriptionBien> biens, ArrayList<Enchere> encheresEnCours) {
        this.login = login;
        this.IBAN = IBAN;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.biens = biens;
        this.encheresEnCours = encheresEnCours;
    }

    public String getLogin() {
        return login;
    }

    public String getIBAN() {
        return IBAN;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public ArrayList<DescriptionBien> getBiens() {
        return biens;
    }

    public ArrayList<Enchere> getEncheresEnCours() {
        return encheresEnCours;
    }    
}
