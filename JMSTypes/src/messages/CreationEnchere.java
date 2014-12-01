package messages;

import java.sql.Timestamp;

/**
 * Message de création d'un objet à enchérir
 *
 * @author Yvan
 */
public class CreationEnchere implements MessageJMSCustom {

    private final int idCreator;
    private final String nom;
    private final String description;
    private final String url;
    private final Timestamp date_depart;
    private final int duree;
    private final double prix_depart;
    private final double increment;
    private final double quantite;

    public CreationEnchere(int idCreator, String nom, String description, String url, Timestamp date_depart, int duree, double prix_depart, double increment, double quantite) {
        this.idCreator = idCreator;
        this.nom = nom;
        this.description = description;
        this.url = url;
        this.date_depart = date_depart;
        this.duree = duree;
        this.prix_depart = prix_depart;
        this.increment = increment;
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Timestamp getDate_depart() {
        return date_depart;
    }

    public int getDuree() {
        return duree;
    }

    public double getPrix_depart() {
        return prix_depart;
    }

    public double getIncrement() {
        return increment;
    }

    public double getQuantite() {
        return quantite;
    }

    public int getIdCreator() {
        return idCreator;
    }

}
