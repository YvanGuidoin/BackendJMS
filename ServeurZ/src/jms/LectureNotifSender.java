package jms;

import basecode.CustomJMSSender;
import basecode.FilesJMS;

/**
 *
 * @author Yvan
 */
public class LectureNotifSender extends CustomJMSSender {
    private static LectureNotifSender INSTANCE = new LectureNotifSender();
    
    private LectureNotifSender(){
        super(FilesJMS.LECTURE_NOTIFS);
    }
    
    public static LectureNotifSender getInstance(){
        return INSTANCE;
    }
}
