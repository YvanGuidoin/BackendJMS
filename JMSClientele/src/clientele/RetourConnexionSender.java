package clientele;


import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class RetourConnexionSender extends CustomJMSSender {

    private static final RetourConnexionSender INSTANCE = new RetourConnexionSender();

    private RetourConnexionSender() {
        super(FilesJMS.RETOUR_CONNEXION);
    }

    public static RetourConnexionSender getInstance() {
        return INSTANCE;
    }
}
