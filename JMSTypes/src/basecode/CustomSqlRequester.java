package basecode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Initialise la connexion à la BDD db
 *
 * @author Yvan
 */
public abstract class CustomSqlRequester {

    /**
     * Connexion à la base de données
     */
    protected Connection conn;

    /**
     * initialise la base de données
     */
    protected CustomSqlRequester() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./db", "sa", "");
            System.out.println("Lancement BDD");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomSqlRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        conn.close();
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

}
