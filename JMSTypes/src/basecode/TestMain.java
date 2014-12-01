package basecode;

/**
 * Classe de test de la librairie
 *
 * @author Yvan
 */
public class TestMain extends Lancement {

    /**
     * Main test
     *
     * @param args arguments de lancement
     */
    public static void main(String[] args) {
        new TestMain().run();
    }

    @Override
    public void onListenersLaunch() {

    }

    @Override
    public void onFilesCompletion() {
        TabAdresse.getInstance().ajouterFile(FilesJMS.RESET);
    }

}
