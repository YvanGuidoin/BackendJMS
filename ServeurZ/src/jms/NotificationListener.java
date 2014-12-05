package jms;

import com.google.gson.Gson;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import launch.LaunchServer;
import basecode.CustomJMSListener;
import basecode.FilesJMS;
import messages.Notification;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpMethod;

/**
 *
 * @author Yvan
 */
public class NotificationListener extends CustomJMSListener {
    
    private HttpClient client;
    
    public NotificationListener(){
        super(FilesJMS.NOTIFICATION);
        try {
            client = new HttpClient();
            client.start();
        } catch (Exception ex) {
            Logger.getLogger(NotificationListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            Notification notif = (Notification) mess.getObject();
            
            Gson gson = new Gson();
            try {
                String adresse = LaunchServer.getNodeJS().getTextIp() + "/notification";
                Request request = client.newRequest(adresse, 80)
                        .method(HttpMethod.POST)
                        .content(new StringContentProvider(gson.toJson(notif)));
                request.send();
            } catch (InterruptedException | TimeoutException | ExecutionException ex) {
                Logger.getLogger(NotificationListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (JMSException ex) {
            Logger.getLogger(NotificationListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
