package objects;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.Timer;
import messages.FinEnchere;

/**
 *
 * @author Yvan
 */
public class BDDUpdater implements Runnable, ActionListener {

    private Timer ticker;

    public BDDUpdater() {
        ticker = new Timer(10000, this);
    }

    @Override
    public void run() {
        ticker.restart();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Calendar now = Calendar.getInstance();
        ArrayList<Integer> list = SqlRequester.getInstance().getBiensIDbyStatut(StatutEnchere.ENCOURS);
        Calendar datefin = Calendar.getInstance();
        for (int bien : list) {
            datefin.setTime(SqlRequester.getInstance().getDateDepart(bien));
            datefin.add(Calendar.HOUR, SqlRequester.getInstance().getDuree(bien));
            if (datefin.before(now.getTime())) {
                BDDUpdater.fermerBien(bien);
            }
        }
    }

    public static void fermerBien(int idBien) {
        SqlRequester.getInstance().updateStatutById(idBien, StatutEnchere.TERMINEE);
        FinEnchereSender.getInstance().send(
                new FinEnchere(
                        SqlRequester.getInstance().getIdClient(idBien),
                        SqlRequester.getInstance().getIdGagnantCourant(idBien),
                        SqlRequester.getInstance().getPrixCalcule(idBien),
                        SqlRequester.getInstance().getDescById(idBien)));
    }

}
