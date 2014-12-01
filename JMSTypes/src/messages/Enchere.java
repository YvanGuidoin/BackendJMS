package messages;

import basecode.Categories;

/**
 * Message décrivant une nouvelle enchère sur un objet
 *
 * @author Yvan
 */
public class Enchere implements MessageJMSCustom {

    private final int idClient;
    private final int idBien;
    private final Categories cat;
    private final double montant;

    public Enchere(int idClient, int idBien, Categories cat, double montant) {
        this.idClient = idClient;
        this.idBien = idBien;
        this.cat = cat;
        this.montant = montant;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdBien() {
        return idBien;
    }

    public double getMontant() {
        return montant;
    }

    public Categories getCat() {
        return cat;
    }

}
