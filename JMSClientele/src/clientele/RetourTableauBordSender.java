package clientele;


import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class RetourTableauBordSender extends CustomJMSSender{
    private static RetourTableauBordSender INSTANCE = new RetourTableauBordSender();
    
    private RetourTableauBordSender(){
        super(FilesJMS.RETOUR_TABLE_BORD);
    }
    
    public static RetourTableauBordSender getInstance(){
        return INSTANCE;
    }
}
