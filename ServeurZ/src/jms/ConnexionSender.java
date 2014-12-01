package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class ConnexionSender extends CustomJMSSender {

    private static final ConnexionSender INSTANCE = new ConnexionSender();

    private ConnexionSender() {
        super(FilesJMS.CONNEXION);
    }

    public static ConnexionSender getInstance() {
        return INSTANCE;
    }
}
