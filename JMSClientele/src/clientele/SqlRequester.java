package clientele;


import com.google.gson.Gson;
import basecode.CustomSqlRequester;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.DescriptionBien;
import messages.Notification;
import messages.TypeNotification;

/**
 *
 * @author Yvan
 */
public class SqlRequester extends CustomSqlRequester {

    private static final SqlRequester INSTANCE = new SqlRequester();
    private Gson gson;

    public static SqlRequester getInstance() {
        return INSTANCE;
    }

    private SqlRequester() {
        super();
        this.gson = new Gson();
    }

    public String getMdp(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT MDP FROM CLIENTELE WHERE ID = '" + id + "'");
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

    public String getLogin(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT LOGIN FROM CLIENTELE WHERE ID = '" + id + "'");
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

    public String getIBAN(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT IBAN FROM CLIENTELE WHERE ID = '" + id + "'");
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

    public String getNom(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT NOM FROM CLIENTELE WHERE ID = '" + id + "'");
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

    public String getPrenom(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT PRENOM FROM CLIENTELE WHERE ID = '" + id + "'");
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

    public String getAdresse(int id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT ADRESSE FROM CLIENTELE WHERE ID = '" + id + "'");
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

    public void addCompte(String login, String mdp, String IBAN, String nom, String prenom, String adresse) {
        try {
            Statement stat = conn.createStatement();
            String req = "INSERT INTO CLIENTELE (LOGIN, MDP, IBAN, NOM, PRENOM, ADRESSE) VALUES ('"
                    + login + "','"
                    + mdp + "','"
                    + IBAN + "','"
                    + nom + "','"
                    + prenom + "','"
                    + adresse + "')";
            System.out.println(req);
            stat.executeUpdate(req);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCompte(int id, String nom, String prenom, String adresse) {
        try {
            Statement stat = conn.createStatement();
            String req = "UPDATE CLIENTELE SET NOM='" + nom + "', PRENOM='" + prenom + "', ADRESSE='" + adresse + "') WHERE ID=" + id + ";";
            System.out.println(req);
            stat.executeUpdate(req);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int testConnexion(String login, String mdp) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT MDP, ID FROM CLIENTELE WHERE LOGIN = '" + login + "'");
            if (res.next()) {
                String res2 = res.getString(1);
                int resist = res.getInt(2);
                res.close();
                stat.close();
                if (res2.equals(mdp)) {
                    return resist;
                } else {
                    return -1;
                }
            } else {
                return -2;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return -2;
        }
    }

    // Notifications
    
    public void addNotification(Notification obj){
        try {
            Statement stat = conn.createStatement();
            String contenu = gson.toJson(obj.getBien());
            String req = "INSERT INTO NOTIFICATION(ID_CLIENT, CONTENU, TYPE_NOTIF, LUE, DATE_CREA) VALUES ('"
                    + obj.getIdClient() + "', '"
                    + contenu + "','"
                    + obj.getType().name() + "', "
                    + Boolean.toString(obj.isLue()) + ", '"
                    + obj.getDate() + "');";
            System.out.println(req);
            stat.executeUpdate(req);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Notification> getNotificationsByClient(int idClient){
        ArrayList<Notification> notifs = new ArrayList<>();
        try{
            Statement stat = conn.createStatement();
            String query = "SELECT CONTENU, TYPE_NOTIF, LUE, DATE_CREA FROM NOTIFICATION"
                    + " WHERE ID_CLIENT = '" + idClient + "'";
            ResultSet res = stat.executeQuery(query);
            while(res.next()){
                DescriptionBien bien = gson.fromJson(res.getCharacterStream(1), DescriptionBien.class);
                TypeNotification notifType = TypeNotification.valueOf(res.getString(2));
                boolean lue = res.getBoolean(3);
                Timestamp date_crea = res.getTimestamp(4);
                Notification notif = new Notification(idClient, bien, notifType, lue, date_crea);
                notifs.add(notif);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notifs;
    }
    
    public ArrayList<Notification> getNotificationsByClientDepuis(int idClient, Timestamp date){
        ArrayList<Notification> notifs = new ArrayList<>();
        try{
            Statement stat = conn.createStatement();
            String query = "SELECT CONTENU, TYPE_NOTIF, LUE, DATE_CREA FROM NOTIFICATION"
                    + " WHERE ID_CLIENT = '" + idClient + "'"
                    + "AND DATE_CREA >= '" + date.toString() + "'";
            ResultSet res = stat.executeQuery(query);
            while(res.next()){
                DescriptionBien bien = gson.fromJson(res.getCharacterStream(1), DescriptionBien.class);
                TypeNotification notifType = TypeNotification.valueOf(res.getString(2));
                boolean lue = res.getBoolean(3);
                Timestamp date_crea = res.getTimestamp(4);
                Notification notif = new Notification(idClient, bien, notifType, lue, date_crea);
                notifs.add(notif);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notifs;
    }
    
    public ArrayList<Notification> getNotificationsNonLuesByClient(int idClient){
        ArrayList<Notification> notifs = new ArrayList<>();
        try{
            Statement stat = conn.createStatement();
            String query = "SELECT CONTENU, TYPE_NOTIF, LUE, DATE_CREA FROM NOTIFICATION"
                    + " WHERE ID_CLIENT = '" + idClient + "' AND LUE = FALSE";
            ResultSet res = stat.executeQuery(query);
            while(res.next()){
                DescriptionBien bien = gson.fromJson(res.getCharacterStream(1), DescriptionBien.class);
                TypeNotification notifType = TypeNotification.valueOf(res.getString(2));
                boolean lue = res.getBoolean(3);
                Timestamp date_crea = res.getTimestamp(4);
                Notification notif = new Notification(idClient, bien, notifType, lue, date_crea);
                notifs.add(notif);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notifs;
    }
    
    public void updateNotifications(int idClient, Timestamp date_lecture){
        try {
            Statement stat = conn.createStatement();
            String req = "UPDATE NOTIFICATION SET LUE='" + Boolean.toString(true)
                    + "' WHERE ID_CLIENT='" + idClient
                    + "' AND DATE_CREA < '" + date_lecture
                    + "';";
            System.out.println(req);
            stat.executeUpdate(req);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
