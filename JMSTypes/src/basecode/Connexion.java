package basecode;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Singleton gérant la connexion au serveur JMS précisé dans la classe Adresse
 *
 * @author Yvan
 */
public class Connexion {

    /**
     * Nom de la factoryName du serveur
     */
    private static final String factoryName = "ConnectionFactory";
    /**
     * Gestion des Connexions uniques par IP
     */
    private static HashMap<Integer, Connexion> INSTANCES;

    /**
     * Context personnalisé selon les paramètres de création
     */
    private Context context;
    /**
     * Factory initialisée dans le constructeur
     */
    private ConnectionFactory factory;

    /**
     * Initialise le singleton
     */
    private Connexion(InetAddress add) {
        try {
            Properties env = new Properties();
            env.put(Context.SECURITY_PRINCIPAL, "admin");
            env.put(Context.SECURITY_CREDENTIALS, "openjms");
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.exolab.jms.jndi.InitialContextFactory");
            env.put(Context.PROVIDER_URL,
                    "tcp://" + add.getHostAddress() + ":3035");

            context = new InitialContext(env);
            factory = (ConnectionFactory) context.lookup(factoryName);

        } catch (NamingException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Accède à la connexion lié à l'adresse add
     *
     * @return Instance de connexion
     */
    public static Connexion getInstance(InetAddress add) {
        if(Connexion.INSTANCES == null) Connexion.INSTANCES = new HashMap<>();
        if (!Connexion.INSTANCES.containsKey(add.hashCode())) {
            Connexion.INSTANCES.put(add.hashCode(), new Connexion(add));
        }
        return Connexion.INSTANCES.get(add.hashCode());
    }

    /**
     * Retourne le context utilisable pour créer une file
     *
     * @return Context utilisable
     */
    public Context getContext() {
        return context;
    }

    /**
     * Retourne la factory utilisable pour créer des files
     *
     * @return Factory utilisable
     */
    public ConnectionFactory getFactory() {
        return factory;
    }
}
