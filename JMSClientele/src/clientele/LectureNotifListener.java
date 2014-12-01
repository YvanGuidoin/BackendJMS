package clientele;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.CustomJMSListener;
import basecode.FilesJMS;
import messages.LectureNotif;

/**
 *
 * @author Yvan
 */
public class LectureNotifListener extends CustomJMSListener {
    
    public LectureNotifListener(){
        super(FilesJMS.LECTURE_NOTIFS);
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            LectureNotif lecture = (LectureNotif) mess.getObject();
            
            SqlRequester.getInstance().updateNotifications(lecture.getIdClient(), lecture.getDate_lecture());
        } catch (JMSException ex) {
            Logger.getLogger(LectureNotifListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
