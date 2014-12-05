package messages;

import java.util.ArrayList;

/**
 * Message détaillant les activités du client
 *
 * @author Yvan
 */
public class ResultReqClient implements MessageJMSCustom {
    
    private static final long serialVersionUID = 7787987897997L;
    private final ArrayList<DescriptionBien> biens;
    private final ArrayList<Enchere> encheresEnCours;

    public ResultReqClient(ArrayList<DescriptionBien> biens, ArrayList<Enchere> encheresEnCours) {
        this.biens = biens;
        this.encheresEnCours = encheresEnCours;
    }

    public ArrayList<DescriptionBien> getBiens() {
        return biens;
    }

    public ArrayList<Enchere> getEncheresEnCours() {
        return encheresEnCours;
    }

}
