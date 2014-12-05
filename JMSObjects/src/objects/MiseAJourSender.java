package objects;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class MiseAJourSender extends CustomJMSSender {
    
    private static final MiseAJourSender INSTANCE = new MiseAJourSender();

    private MiseAJourSender() {
        super(FilesJMS.MISE_A_JOUR);
    }
    
    public static MiseAJourSender getInstance(){
        return INSTANCE;
    }
}
