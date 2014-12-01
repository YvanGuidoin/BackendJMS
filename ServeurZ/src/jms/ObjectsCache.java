package jms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import basecode.Categories;
import messages.DescriptionBien;

/**
 *
 * @author Yvan
 */
public class ObjectsCache {

    private static ObjectsCache INSTANCE = new ObjectsCache();

    private HashMap<Integer, DescriptionBien> donnees;

    private ObjectsCache() {
        donnees = new HashMap<>();
    }

    public static ObjectsCache getInstance() {
        return INSTANCE;
    }

    public void init() {

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
  
    public ArrayList<DescriptionBien> researchByParams(String name, String desc, Categories cat) {
        ArrayList<DescriptionBien> ret = new ArrayList<>();
        for (Map.Entry<Integer, DescriptionBien> entrySet : donnees.entrySet()) {
            DescriptionBien value = entrySet.getValue();
            if (correspondToParams(value, name, desc, cat)) {
                ret.add(value);
            }
        }
        return ret;
    }
    
    private boolean correspondToParams(DescriptionBien bien, String name, String desc, Categories cat){
        if(cat != null && !bien.getCategorie().equals(cat)) return false;
        if(name != null && !bien.getNom().contains(name)) return false;
        if(desc != null && !bien.getDescription().contains(desc)) return false;
        return true;
    }
}
