package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class ResetSender extends CustomJMSSender{
    
    private static ResetSender INSTANCE = new ResetSender();
    
    private ResetSender(){
        super(FilesJMS.RESET);
    }
    
    public static ResetSender getInstance(){
        return INSTANCE;
    }
    
}
