package banque;


import lib.CustomJMSSender;
import lib.FilesJMS;

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
