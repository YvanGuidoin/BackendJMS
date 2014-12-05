package basecode;

/**
 * Nom des files JMS dans le système
 *
 * @author Yvan
 */
public enum FilesJMS {

    ANNUAIRE("annuaire"),
    CONNEXION("connexion"),
    CREATION_CLIENT("creationClient"),
    CREATION_ENCHERES("creationObjects"),
    DEMANDE_NOTIF("demandeNotif"),
    ENCHERES("encheres"),
    FERMETURE_ENCHERE("fermetureEnchere"),
    FIN_ENCHERE("finEnchere"),
    LECTURE_NOTIFS("lectureNotifs"),
    MISE_A_JOUR("miseAJour"),
    NOTIFICATION("notification"),
    NOTIFICATION_ENCHERE("notificationEnchere"),
    OPERATION("operation"),
    REQ_CLIENT("reqClient"),
    RESET("reset"),
    RETOUR_DEMAND_NOTIF("retourDemandeNotif"),
    RETOUR_BANQUE("retourOperation"),
    RETOUR_CONNEXION("retourConnexion"),
    RETOUR_REQ_CLIENT("retourReqClient"),
    RETOUR_TABLE_BORD("retourTablBord"),
    TABLE_BORD("tableauBord");

    /**
     * nom JMS
     */
    private String value = "";

    /**
     * Crée un élément Files
     *
     * @param value nom de la file
     */
    FilesJMS(String value) {
        this.value = value;
    }

    /**
     * Renvoie le nom de la file JMS
     *
     * @return nom de la file
     */
    public String getValue() {
        return value;
    }

}
