package clientele;


import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class OperationSender extends CustomJMSSender {

    private static final OperationSender INSTANCE = new OperationSender();

    private OperationSender() {
        super(FilesJMS.OPERATION);
    }

    public static OperationSender getInstance() {
        return INSTANCE;
    }

}
