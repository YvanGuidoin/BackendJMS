package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class CreationEnchereSender extends CustomJMSSender {
    private static CreationEnchereSender INSTANCE = new CreationEnchereSender();
    
    private CreationEnchereSender(){
        super(FilesJMS.CREATION_ENCHERES);
    }
    
    public static CreationEnchereSender getInstance(){
        return INSTANCE;
    }
}
