package webservice.data;

/**
 *
 * @author Yvan
 */
public class EnchereObjectFromJson {
    private int idClient;
    private int idObject;
    private double montant;
    private String categorie;

    public EnchereObjectFromJson() {
    }

    public int getIdClient() {
        return idClient;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getIdObject() {
        return idObject;
    }

    public double getMontant() {
        return montant;
    }
    
}
