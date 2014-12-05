package jms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import basecode.Categories;
import messages.DescriptionBien;
import messages.Reset;

/**
 *
 * @author Yvan
 */
public class ObjectsCache {

    private static ObjectsCache INSTANCE = new ObjectsCache();

    private HashMap<Integer, DescriptionBien> donnees;

    private ObjectsCache() {
        donnees = new HashMap<>();
        System.out.println("Initialisation cache objet");
    }

    public static ObjectsCache getInstance() {
        return INSTANCE;
    }

    public void init() {
        ResetSender.getInstance().send(new Reset(), Categories.ELECTROMENAGER.asDeterminant());
        ResetSender.getInstance().send(new Reset(), Categories.HIFI.asDeterminant());
        ResetSender.getInstance().send(new Reset(), Categories.INFORMATIQUE.asDeterminant());
    }

    public void put(DescriptionBien bien) {
        donnees.put(bien.getId(), bien);
    }

    public ArrayList<DescriptionBien> getAll() {
        ArrayList<DescriptionBien> ret = new ArrayList();
        ret.addAll(donnees.values());
        return ret;
    }

    public DescriptionBien getById(int id) {
        return donnees.get(id);
    }
  
    public ArrayList<DescriptionBien> researchByParams(String research, String cat) {
        ArrayList<DescriptionBien> ret = new ArrayList<>();
        for (Map.Entry<Integer, DescriptionBien> entrySet : donnees.entrySet()) {
            DescriptionBien value = entrySet.getValue();
            if (correspondToParams(value, research, research, cat)) {
                ret.add(value);
            }
        }
        return ret;
    }
    
    private boolean correspondToParams(DescriptionBien bien, String name, String desc, String cat){
        boolean research = false;
        if(cat != null && bien.getCategorie().name().equals(cat)) research = Boolean.logicalOr(research, true);
        if(name != null && bien.getNom().contains(name)) research = Boolean.logicalOr(research, true);
        if(desc != null && bien.getDescription().contains(desc)) research = Boolean.logicalOr(research, true);
        return research;
    }
}
