package banque;


import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class ResultOperationSender extends CustomJMSSender {

    private static final ResultOperationSender INSTANCE = new ResultOperationSender();

    private ResultOperationSender() {
        super(FilesJMS.RETOUR_BANQUE);
    }

    public static ResultOperationSender getInstance() {
        return INSTANCE;
    }
}
