package objects;


import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class FinEnchereSender extends CustomJMSSender {

    private static final FinEnchereSender INSTANCE = new FinEnchereSender();

    private FinEnchereSender() {
        super(FilesJMS.FIN_ENCHERE);
    }

    public static FinEnchereSender getInstance() {
        return INSTANCE;
    }
}
