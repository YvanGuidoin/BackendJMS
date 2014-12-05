package webservice;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jms.CreationSender;
import jms.DemandeNotifSender;
import jms.LectureNotifSender;
import jms.TableauBordSender;
import basecode.CustomJMSReceiver;
import basecode.FilesJMS;
import messages.CreationClient;
import messages.DemandeNotif;
import messages.LectureNotif;
import messages.RetourTableauBord;
import messages.TableauBord;
import webservice.data.DataInscriptionFromJson;

/**
 *
 * @author Yvan
 */
public class ClientServlet extends HttpServlet {
    
    private static int idRequest = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path != null){
            StringTokenizer token = new StringTokenizer(path, "/");
            String idClient = token.nextToken();
            String chemin = token.nextToken();
            Gson gson = new Gson();
            Chemin option = Chemin.UNKNOWN;
            if(idClient != null && chemin != null){
                
                if("tableau".equals(chemin)) option = Chemin.TABLEAU;
                if("infos".equals(chemin)) option = Chemin.NOTIFS;
                if("notifications".equals(chemin)) option = Chemin.NOTIFS;
                int idCl = Integer.parseInt(idClient);
                
                switch(option){
                    case TABLEAU : 
                        idRequest++;
                        TableauBord mess = new TableauBord(idCl,true);
                        TableauBordSender.getInstance().send(mess,idRequest+"");
                        RetourTableauBord retour = (RetourTableauBord) CustomJMSReceiver.receive(FilesJMS.RETOUR_TABLE_BORD, "JMSType = '" + idRequest+"'");
                        resp.getWriter().print(gson.toJson(retour)); break;
                    case INFOS : 
                        idRequest++;
                        TableauBord mess2 = new TableauBord(idCl,false);
                        TableauBordSender.getInstance().send(mess2,idRequest+"");
                        RetourTableauBord retour2 = (RetourTableauBord) CustomJMSReceiver.receive(FilesJMS.RETOUR_TABLE_BORD, "JMSType = '" + idRequest+"'");
                        resp.getWriter().print(gson.toJson(retour2)); break;
                    case NOTIFS :
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.WEEK_OF_YEAR, -1);
                        DemandeNotif demande = new DemandeNotif(idCl, new Timestamp(cal.getTimeInMillis()), false);
                        DemandeNotifSender.getInstance().send(demande);
                        resp.getWriter().print(gson.toJson("OK")); break;
                    default: 
                        resp.getWriter().print(gson.toJson("No such service")); break;       
                }
                
            } else {
                resp.getWriter().print(gson.toJson("No such service"));
            }
            
            
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        DataInscriptionFromJson data = gson.fromJson(req.getReader(), DataInscriptionFromJson.class);
        System.out.println("username : " + data.getUsername());
        System.out.println("password : " + data.getPassword());
        System.out.println("iban : " + data.getIban());
        System.out.println("prenom : " + data.getPrenom());
        System.out.println("nom : " + data.getNom());
        System.out.println("adresse : " + data.getAdresse());

        CreationSender.getInstance().send(
                new CreationClient(
                        data.getUsername(),
                        data.getPassword(),
                        data.getIban(),
                        data.getNom(),
                        data.getPrenom(),
                        data.getAdresse()));

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(gson.toJson("OK"));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if(path != null){
            StringTokenizer token = new StringTokenizer(path, "/");
            String idClient = token.nextToken();
            String chemin = token.nextToken();
            Gson gson = new Gson();
            Chemin option = Chemin.UNKNOWN;
            if(idClient != null && chemin != null){
                
                if("notifications".equals(chemin)) option = Chemin.LECTURE_NOTIFS;
                int idCl = Integer.parseInt(idClient);
                
                switch(option){
                    case LECTURE_NOTIFS : 
                        Calendar calendar = Calendar.getInstance();
                        LectureNotif lectureNotif = new LectureNotif(idCl, new Timestamp(calendar.getTimeInMillis()));
                        LectureNotifSender.getInstance().send(lectureNotif);
                        resp.getWriter().print(gson.toJson("OK")); break;
                    default: 
                        resp.getWriter().print(gson.toJson("No such service")); break;    
                }
                
            } else {
                resp.getWriter().print(gson.toJson("No such service"));
            }
            
            
        }
    }
    
    enum Chemin{
        INFOS,
        NOTIFS,
        TABLEAU,
        LECTURE_NOTIFS,
        UNKNOWN
    }
}
