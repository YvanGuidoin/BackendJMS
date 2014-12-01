package messages;

import java.io.Serializable;

/**
 * Retour possible de la transaction bancaire
 *
 * @author Yvan
 */
public enum Transaction implements Serializable {

    REUSSIE,
    ECHEC
}
