package objects;


import basecode.Categories;
import basecode.FilesJMS;
import basecode.Lancement;
import basecode.TabAdresse;

/**
 *
 * @author Yvan
 */
public class Biens extends Lancement {

    private static Categories categorie;

    @Override
    public void onParamsSet() {
        this.setTitle("Serveur d'objets");
        this.setOpenMessage("Serveur d'objets lance");
    }
    
    @Override
    public void onListenersLaunch() {
        ReqClientListener reqClientListener = new ReqClientListener();
        CreationEnchereListener creationEnchereListener = new CreationEnchereListener();
        EnchereListener enchereListener = new EnchereListener();
        ResetListener resetListener = new ResetListener();
        FermetureEnchereListener fermetureEnchereListener = new FermetureEnchereListener();
    }

    @Override
    public void onThreadsLaunch() {
        super.onThreadsLaunch();
        if(categorie == null) categorie = new CustomCategoriesInput().showDialog();
        System.out.println("Categorie : " + categorie.toString());
        new Thread(new BDDUpdater()).start();
    }

    public static void main(String[] args) {
        if(args.length > 0) Biens.setCategorie(Categories.valueOf(args[0]));
        new Biens().run();
    }

    public static Categories getCategorie() {
        return categorie;
    }
    
    public static void setCategorie(Categories cat){
        categorie = cat;
    }

    @Override
    public void onFilesCompletion() {
        TabAdresse.getInstance()
                .ajouterFile(FilesJMS.CREATION_ENCHERES)
                .ajouterFile(FilesJMS.ENCHERES)
                .ajouterFile(FilesJMS.FERMETURE_ENCHERE)
                .ajouterFile(FilesJMS.FIN_ENCHERE)
                .ajouterFile(FilesJMS.MISE_A_JOUR)
                .ajouterFile(FilesJMS.NOTIFICATION)
                .ajouterFile(FilesJMS.NOTIFICATION_ENCHERE)
                .ajouterFile(FilesJMS.REQ_CLIENT)
                .ajouterFile(FilesJMS.RESET)
                .ajouterFile(FilesJMS.RETOUR_REQ_CLIENT);
    }
}
