package basecode;

/**
 * Catégories d'objets possibles
 *
 * @author Yvan
 */
public enum Categories {

    INFORMATIQUE,
    ELECTROMENAGER,
    HIFI;
    
    public String asDeterminant(){
        return (name());
    }
}
