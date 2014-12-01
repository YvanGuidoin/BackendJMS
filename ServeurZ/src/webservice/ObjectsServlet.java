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
import jms.CreationSender;
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
        String name = req.getParameter("name");
        String categorie = req.getParameter("categorie");
        String description = req.getParameter("description");
        String id = req.getParameter("id");
        ArrayList<DataObjectFromJson> objs = new ArrayList<>();
        
        //sélection par id
        if(id != null){
            objs.add(DataObjectFromJson.DataObjectFromDescriptionBien(
                ObjectsCache.getInstance().getById(Integer.parseInt(id))));
        } else {
            
            ArrayList<DescriptionBien> biens = ObjectsCache.getInstance().getAll();
            for (DescriptionBien bien : biens) {
                objs.add(DataObjectFromJson.DataObjectFromDescriptionBien(bien));
            }
            
        }
        
        resp.getWriter().print(gson.toJson(objs.toArray()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DataObjectFromJson data = gson.fromJson(req.getReader(), DataObjectFromJson.class);
        
        Calendar now = Calendar.getInstance();
        CreationEnchere message = new CreationEnchere(
                data.getId(), 
                data.getNom(), 
                data.getDescription(), 
                data.getUrlImage(), 
                new Timestamp(now.getTimeInMillis()), 
                data.getDuree(), 
                data.getPrixDepart(), 
                data.getIncrement(), 
                data.getQuantite());
        CreationSender.getInstance().send(message, data.getCategorie());
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
        } else {
            resp.getWriter().print(gson.toJson("Non supporté : service /CATEGORIE/IDOBJET"));
            resp.setStatus(resp.SC_BAD_REQUEST);
        }
    }
}
