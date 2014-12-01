package objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import basecode.CustomJMSListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import basecode.FilesJMS;
import messages.FermetureEnchere;

/**
 *
 * @author Yvan
 */
public class FermetureEnchereListener extends CustomJMSListener {

    public FermetureEnchereListener() {
        super(FilesJMS.FERMETURE_ENCHERE, "JMSType = '"+ Biens.getCategorie().asDeterminant() + "'");
    }

    @Override
    public void onMessage(Message msg) {
        try {
            ObjectMessage mess = (ObjectMessage) msg;
            FermetureEnchere crea = (FermetureEnchere) mess.getObject();

            BDDUpdater.fermerBien(crea.getIdBien());

        } catch (JMSException ex) {
            Logger.getLogger(EnchereListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
