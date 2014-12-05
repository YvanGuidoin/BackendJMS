package messages;

import java.util.Map;
import basecode.Adresse;
import basecode.FilesJMS;

/**
 * Transporte les données de localisation des files JMS
 * @author Yvan
 */
public class AnnuaireInfos implements MessageJMSCustom {
    
    private static final long serialVersionUID = 6246924698428962L;
    final private Map<FilesJMS, Adresse> data;

    public AnnuaireInfos(Map<FilesJMS, Adresse> data) {
        this.data = data;
    }

    public Map<FilesJMS, Adresse> getData() {
        return data;
    }
}
