package webservice.data;

/**
 *
 * @author Yvan
 */
public class DataRetourConnexionFromJson {

    private String idClient;
    private String etat;

    public DataRetourConnexionFromJson() {
    }

    public String getEtat() {
        return etat;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

}
