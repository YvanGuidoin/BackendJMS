package webservice;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jms.ConnexionSender;
import basecode.CustomJMSReceiver;
import basecode.FilesJMS;
import messages.ConnexionRetour;
import messages.ConnexionTentative;
import webservice.data.DataConnexionFromJson;
import webservice.data.DataRetourConnexionFromJson;

/**
 *
 * @author Yvan
 */
public class ConnexionServlet extends HttpServlet {

    private int tentativeId = 0;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DataConnexionFromJson data = gson.fromJson(req.getReader(), DataConnexionFromJson.class);
        System.out.println("username : " + data.getUsername());
        System.out.println("password : " + data.getPassword());

        tentativeId++;

        ConnexionSender.getInstance().send(new ConnexionTentative(data.getUsername(), data.getPassword(), tentativeId));
        ConnexionRetour ret = (ConnexionRetour) CustomJMSReceiver.receive(FilesJMS.RETOUR_CONNEXION, "JMSType = '" + tentativeId + "'");

        System.out.println("ID de tentative " + tentativeId + " - etat : " + ret.getEtat());

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        DataRetourConnexionFromJson res = new DataRetourConnexionFromJson();
        res.setIdClient(ret.getIdClient() + "");
        res.setEtat(ret.getEtat().toString());
        resp.getWriter().println(gson.toJson(res));
    }
}
