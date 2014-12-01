package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class TableauBordSender extends CustomJMSSender {
    private static TableauBordSender INSTANCE = new TableauBordSender();
    
    private TableauBordSender(){
        super(FilesJMS.TABLE_BORD);
    }
    
    public static TableauBordSender getInstance(){
        return INSTANCE;
    }
}
