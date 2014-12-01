package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class EnchereSender extends CustomJMSSender {
    private static EnchereSender INSTANCE = new EnchereSender();
    
    private EnchereSender(){
        super(FilesJMS.ENCHERES);
    }
    
    public static EnchereSender getInstance(){
        return INSTANCE;
    }
}
