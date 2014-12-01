package objects;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import basecode.CustomSqlRequester;
import messages.DescriptionBien;
import messages.Enchere;

/**
 *
 * @author Yvan
 */
public class SqlRequester extends CustomSqlRequester {

    private static final SqlRequester INSTANCE = new SqlRequester();

    public static SqlRequester getInstance() {
        return INSTANCE;
    }

    private SqlRequester() {
        super();
    }

    //TABLE BIEN
    public int getId(String nom, String description) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ID FROM BIEN WHERE NOM = '" + nom + "' AND DESCRIPTION='" + description + "'");
            res.next();
            int idObjet = res.getInt(1);
            res.close();
            stat.close();
            return idObjet;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public String getNom(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT NOM FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            String str = res.getString(1);
            res.close();
            stat.close();
            return str;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public String getDescription(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT DESCRIPTION FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            String str = res.getString(1);
            res.close();
            stat.close();
            return str;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public String getURL(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT URL FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            String str = res.getString(1);
            res.close();
            stat.close();
            return str;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public Timestamp getDateDepart(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT DATE_DEPART FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            Timestamp datedepart = res.getTimestamp(1);
            res.close();
            stat.close();
            return datedepart;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int getDuree(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT DUREE FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            int duree = res.getInt(1);
            res.close();
            stat.close();
            return duree;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public double getPrixDepart(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT PRIX_DEPART FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            double prixDepart = res.getDouble(1);
            res.close();
            stat.close();
            return prixDepart;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public double getPrixCalcule(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT PRIX_CALCULE FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            double prixCalcule = res.getDouble(1);
            res.close();
            stat.close();
            return prixCalcule;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public double getQuantite(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT QUANTITE FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            double qte = res.getDouble(1);
            res.close();
            stat.close();
            return qte;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getIdGagnantCourant(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ID_GAGNANT_COURANT FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            int idcourantGagnant = res.getInt(1);
            res.close();
            stat.close();
            return idcourantGagnant;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public double getIncremental(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT INCREMENTAL FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            double idcourantGagnant = res.getDouble(1);
            res.close();
            stat.close();
            return idcourantGagnant;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0.0;
        }
    }

    public int addBien(String nom, String description, String URL, Timestamp date_depart, int duree, double prix_depart, double prix_calcule, double incremental, double quantite, int id_gagnant_courant, StatutEnchere statut) {
        try {
            Statement stat = conn.createStatement();
            String req = "INSERT INTO BIEN (NOM, DESCRIPTION, URL, DATE_DEPART, DUREE, PRIX_DEPART, PRIX_CALCULE, INCREMENTAL, QUANTITE, ID_GAGNANT_COURANT, STATUT) VALUES ('"
                    + nom + "','"
                    + description + "','"
                    + URL + "','"
                    + date_depart + "','"
                    + duree + "','"
                    + prix_depart + "','"
                    + prix_calcule + "','"
                    + incremental + "','"
                    + quantite + "','"
                    + id_gagnant_courant + ");"
                    + statut.toString() + "','";
            System.out.println(req);
            stat.executeUpdate(req);
            String req2 = "CALL SCOPE_IDENTITY()";
            ResultSet retour = stat.executeQuery(req2);
            retour.next();
            int id = retour.getInt(1);
            retour.close();
            stat.close();
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public StatutEnchere getStatut(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT STATUT FROM BIEN WHERE ID = '" + id + "'");
            res.next();
            StatutEnchere statut = StatutEnchere.valueOf(res.getString(1));
            res.close();
            stat.close();
            return statut;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //TABLE ENCHERE
    public double getMontantMax(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT MONTANT_MAX FROM ENCHERE WHERE ID = '" + id + "'");
            res.next();
            double prixCalcule = res.getDouble(1);
            res.close();
            stat.close();
            return prixCalcule;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getIdObjet(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ID_OBJET FROM ENCHERE WHERE ID = '" + id + "'");
            res.next();
            int prixCalcule = res.getInt(1);
            res.close();
            stat.close();
            return prixCalcule;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getIdClient(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ID_CLIENT FROM ENCHERE WHERE ID = '" + id + "'");
            res.next();
            int prixCalcule = res.getInt(1);
            res.close();
            stat.close();
            return prixCalcule;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    //UPDATES
    public void addEnchere(double montantMax, int id_objet, int id_client) {
        try {
            Statement stat = conn.createStatement();
            String req = "INSERT INTO ENCHERE (MONTANT_MAX, ID_OBJET, ID_CLIENT) VALUES ('" + montantMax + "','" + id_objet + "','" + id_client + ");";
            System.out.println(req);
            stat.executeUpdate(req);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateMontantActuel(int id, double montant) {
        try {
            Statement stat = conn.createStatement();
            String req = "UPDATE BIEN SET PRIX_CALCULE='" + montant + "') WHERE ID=" + id + "; ";
            System.out.println(req);
            stat.executeUpdate(req);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateGagnantActuel(int id, int idGagnant) {
        try {
            Statement stat = conn.createStatement();
            String req = "UPDATE BIEN SET ID_GAGNANT_COURANT='" + idGagnant + "') WHERE ID=" + id + "; ";
            System.out.println(req);
            stat.executeUpdate(req);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStatutById(int id, StatutEnchere statut) {
        try {
            Statement stat = conn.createStatement();
            String req = "UPDATE BIEN SET STATUT='" + statut.toString() + "') WHERE ID=" + id + "; ";
            System.out.println(req);
            stat.executeUpdate(req);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Enchere> getEncheresByBien(int idBien) {
        ArrayList<Enchere> list = new ArrayList<Enchere>();
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ID_CLIENT, ID_OBJET, MONTANT_MAX FROM ENCHERE WHERE ID_OBJET = '" + idBien + "'");
            while (res.next()) {
                list.add(new Enchere(res.getInt(1), res.getInt(2), Biens.getCategorie(), res.getDouble(3)));
            }
            res.close();
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Enchere> getEncheresByBienClient(int idBien, int idClient) {
        ArrayList<Enchere> list = new ArrayList<Enchere>();
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ID_CLIENT, ID_OBJET, MONTANT_MAX FROM ENCHERE WHERE ID_OBJET = '" + idBien + "' AND ID_CLIENT = '" + idClient + "'");
            while (res.next()) {
                list.add(new Enchere(res.getInt(1), res.getInt(2), Biens.getCategorie(), res.getDouble(3)));
            }
            res.close();
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Integer> getBiensIDbyStatut(StatutEnchere statut) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ID FROM BIEN WHERE STATUT = '" + statut.toString() + "'");
            while (res.next()) {
                list.add(res.getInt(1));
            }
            res.close();
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public DescriptionBien getDescById(int idBien) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ID, ID_CREATOR, NOM, DESCRIPTION, URL, DATE_DEPART, DUREE, PRIX_DEPART, PRIX_CALCULE, INCERMENTAL, QUANTITE, ID_GAGNANT_COURANT FROM BIEN WHERE ID = '" + idBien + "'");
            res.next();
            DescriptionBien desc = new DescriptionBien(
                    res.getInt(1),
                    res.getInt(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getTimestamp(6),
                    res.getInt(7),
                    res.getDouble(8),
                    res.getDouble(9),
                    res.getDouble(10),
                    res.getDouble(11),
                    res.getInt(12),
                    Biens.getCategorie());
            res.close();
            stat.close();
            return desc;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Integer> getBiensByIDClient(int idClient) {
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ID FROM BIEN WHERE ID_CREATOR = '" + idClient + "' OR ID_GAGNANT_COURANT = '" + idClient + "'");
            while (res.next()) {
                list.add(res.getInt(1));
            }
            res.close();
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
