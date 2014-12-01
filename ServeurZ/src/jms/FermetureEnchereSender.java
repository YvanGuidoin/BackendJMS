package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class FermetureEnchereSender extends CustomJMSSender {
    private static FermetureEnchereSender INSTANCE = new FermetureEnchereSender();
    
    private FermetureEnchereSender(){
        super(FilesJMS.FERMETURE_ENCHERE);
    }
    
    public static FermetureEnchereSender getInstance(){
        return INSTANCE;
    }
}
