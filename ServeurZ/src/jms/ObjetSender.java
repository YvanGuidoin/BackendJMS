package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Teddy
 */
public class ObjetSender extends CustomJMSSender {

    private static final ObjetSender INSTANCE = new ObjetSender();

    private ObjetSender() {
        super(FilesJMS.CREATION_ENCHERES);
    }

    public static ObjetSender getInstance() {
        return INSTANCE;
    }

}
