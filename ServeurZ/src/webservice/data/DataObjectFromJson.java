package webservice.data;

import java.sql.Timestamp;
import messages.DescriptionBien;

/**
 *
 * @author Yvan
 */
public class DataObjectFromJson {

    private int id;
    private String nom;
    private int idCreator;
    private String description;
    private String urlImage;
    private String dateDepart;
    private int duree;
    private double prixDepart;
    private double prixCalcule;
    private double increment;
    private double quantite;
    private String categorie;

    public DataObjectFromJson() {
    }

    public static DataObjectFromJson DataObjectFromDescriptionBien(DescriptionBien desc) {
        DataObjectFromJson obj = new DataObjectFromJson();
        obj.id = desc.getId();
        obj.nom = desc.getNom();
        obj.idCreator = desc.getIdCreator();
        obj.description = desc.getDescription();
        obj.urlImage = desc.getUrl();
        obj.dateDepart = desc.getDate_depart().toString();
        obj.duree = desc.getDuree();
        obj.prixDepart = desc.getPrix_depart();
        obj.prixCalcule = desc.getPrix_calcule();
        obj.increment = desc.getIncrement();
        obj.quantite = desc.getQuantite();
        obj.categorie = desc.getCategorie().toString();
        return obj;
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

    public String getUrlImage() {
        return urlImage;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public int getDuree() {
        return duree;
    }

    public double getPrixDepart() {
        return prixDepart;
    }

    public double getPrixCalcule() {
        return prixCalcule;
    }

    public double getIncrement() {
        return increment;
    }

    public double getQuantite() {
        return quantite;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getIdCreator() {
        return idCreator;
    }

}
