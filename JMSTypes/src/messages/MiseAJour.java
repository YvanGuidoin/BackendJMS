package messages;

/**
 * Message notifiant une mise à jour d'un objet enchérissable au serveur Z
 *
 * @author Yvan
 */
public class MiseAJour implements MessageJMSCustom {

    private static final long serialVersionUID = 6942894829849L;
    private final DescriptionBien objet;

    public MiseAJour(DescriptionBien objet) {
        this.objet = objet;
    }

    public DescriptionBien getObjet() {
        return objet;
    }

}
