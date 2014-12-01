package banque;


import lib.CustomSqlRequester;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public boolean existIBAN(String id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT * FROM BANQUE WHERE IBAN = '" + id + "'");
            boolean existence = res.first();
            res.close();
            stat.close();
            return existence;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public double getSolde(String id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT SOLDE FROM BANQUE WHERE IBAN = '" + id + "'");
            res.next();
            Double d = res.getDouble(1);
            res.close();
            stat.close();
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0.0;
        }
    }

    public double getDecouvertAutorise(String id) {
        try {
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("SELECT DECOUVERT FROM BANQUE WHERE IBAN = '" + id + "'");
            res.next();
            Double d = res.getDouble(1);
            res.close();
            stat.close();
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
            return 0.0;
        }
    }

    public void addSolde(String id, double montant) {
        try {
            Statement stat = conn.createStatement();
            String req = "UPDATE BANQUE SET SOLDE = SOLDE + '" + montant + "' WHERE IBAN = '" + id + "'";
            System.out.println(req);
            stat.executeUpdate(req);
            stat.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
