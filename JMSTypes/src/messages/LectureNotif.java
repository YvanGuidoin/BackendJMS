package messages;

import java.sql.Timestamp;

/**
 *
 * @author Yvan
 */
public class LectureNotif implements MessageJMSCustom {
    
    private static final long serialVersionUID = 6984269849626L;
    final private int idClient;
    final private Timestamp date_lecture;

    public LectureNotif(int idClient, Timestamp date_lecture) {
        this.idClient = idClient;
        this.date_lecture = date_lecture;
    }

    public int getIdClient() {
        return idClient;
    }

    public Timestamp getDate_lecture() {
        return date_lecture;
    }
}
