package clientele;


import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class ReqClientSender extends CustomJMSSender {

    private static final ReqClientSender INSTANCE = new ReqClientSender();

    private ReqClientSender() {
        super(FilesJMS.REQ_CLIENT);
    }

    public static ReqClientSender getInstance() {
        return INSTANCE;
    }

}
