package webservice;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jms.CreationEnchereSender;
import jms.FermetureEnchereSender;
import jms.ObjectsCache;
import messages.CreationEnchere;
import messages.DescriptionBien;
import messages.FermetureEnchere;
import webservice.data.DataObjectFromJson;

/**
 *
 * @author Yvan
 */
public class ObjectsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String categorie = req.getParameter("categorie");
        String research = req.getParameter("research");
        String id = req.getParameter("id");
        ArrayList<DataObjectFromJson> objs = new ArrayList<>();
        
        //sélection par id
        if(id != null){
            objs.add(DataObjectFromJson.DataObjectFromDescriptionBien(
                ObjectsCache.getInstance().getById(Integer.parseInt(id))));
        } else {
            ArrayList<DescriptionBien> biens;
            if(categorie != null || research!= null){
                System.out.println("Recherche d'objets : (" + categorie + ";" + research + ")");
                biens = ObjectsCache.getInstance().researchByParams(research, categorie);
            } else {
                biens = ObjectsCache.getInstance().getAll();
            }
            for (DescriptionBien bien : biens) {
                objs.add(DataObjectFromJson.DataObjectFromDescriptionBien(bien));
            }   
        }
        resp.getWriter().print(gson.toJson(objs));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DataObjectFromJson data = gson.fromJson(req.getReader(), DataObjectFromJson.class);
        System.out.print("Inscription objet :");
        System.out.println(req.getReader());
        
        Calendar now = Calendar.getInstance();
        CreationEnchere message = new CreationEnchere(
                data.getIdCreator(),
                data.getNom(),
                data.getDescription(),
                data.getUrlImage(),
                new Timestamp(now.getTimeInMillis()),
                data.getDuree(),
                data.getPrixDepart(),
                data.getIncrement(),
                data.getQuantite());
        CreationEnchereSender.getInstance().send(message, data.getCategorie());
        resp.getWriter().print("{\"OK\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        Gson gson = new Gson();
        if(path != null){
            StringTokenizer token = new StringTokenizer(path, "/");
            String categorie = token.nextToken();
            String idObjet = token.nextToken();
            
            FermetureEnchereSender.getInstance().send(
                    new FermetureEnchere(Integer.parseInt(idObjet)),
                    categorie);
            System.out.println("Suppression de l'objet : " + idObjet);
        } else {
            resp.getWriter().print(gson.toJson("Non supporté : service /CATEGORIE/IDOBJET"));
            resp.setStatus(resp.SC_BAD_REQUEST);
        }
    }
}
