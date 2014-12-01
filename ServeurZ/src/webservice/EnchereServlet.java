package webservice;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jms.EnchereSender;
import basecode.Categories;
import messages.Enchere;
import webservice.data.EnchereObjectFromJson;

/**
 *
 * @author Yvan
 */
public class EnchereServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        EnchereObjectFromJson enchere = gson.fromJson(req.getReader(), EnchereObjectFromJson.class);
        
        Enchere ench = new Enchere(enchere.getIdClient(), enchere.getIdObject(), Categories.valueOf(enchere.getCategorie()), enchere.getMontant());
        EnchereSender.getInstance().send(ench, enchere.getCategorie());
    }
    
}
