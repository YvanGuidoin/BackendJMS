package messages;

import java.io.Serializable;
import java.sql.Timestamp;
import basecode.Categories;
import java.util.ArrayList;

/**
 * Classe décrivant un bien en détail
 *
 * @author Yvan
 */
public class DescriptionBien implements Serializable {

    private static final long serialVersionUID = 6824946982849624L;
    private final int id;
    private final int idCreator;
    private final String nom;
    private final String description;
    private final String url;
    private final Timestamp date_depart;
    private final int duree;
    private final double prix_depart;
    private final double prix_calcule;
    private final double increment;
    private final double quantite;
    private final int idGagnant;
    private final Categories categorie;
    private final ArrayList<Enchere> encheresEnCours;

    public DescriptionBien(int id, int idCreator, String nom, String description, String url, Timestamp date_depart, int duree, double prix_depart, double prix_calcule, double increment, double quantite, int idGagnant, Categories categorie, ArrayList<Enchere> encheresEnCours) {
        this.id = id;
        this.idCreator = idCreator;
        this.nom = nom;
        this.description = description;
        this.url = url;
        this.date_depart = date_depart;
        this.duree = duree;
        this.prix_depart = prix_depart;
        this.prix_calcule = prix_calcule;
        this.increment = increment;
        this.quantite = quantite;
        this.idGagnant = idGagnant;
        this.categorie = categorie;
        this.encheresEnCours = encheresEnCours;
    }

    public int getId() {
        return id;
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

    public double getPrix_calcule() {
        return prix_calcule;
    }

    public double getIncrement() {
        return increment;
    }

    public double getQuantite() {
        return quantite;
    }

    public int getIdGagnant() {
        return idGagnant;
    }

    public Categories getCategorie() {
        return categorie;
    }

    public int getIdCreator() {
        return idCreator;
    }
    
    public ArrayList<Enchere> getEncheresEnCours() {
        return encheresEnCours;
    }  
}
