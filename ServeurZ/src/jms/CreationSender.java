package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Teddy
 */
public class CreationSender extends CustomJMSSender {

    private static final CreationSender INSTANCE = new CreationSender();

    private CreationSender() {
        super(FilesJMS.CREATION_CLIENT);
    }

    public static CreationSender getInstance() {
        return INSTANCE;
    }

}
