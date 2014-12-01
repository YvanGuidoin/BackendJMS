package objects;


import basecode.CustomJMSSender;
import basecode.FilesJMS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yvan
 */
public class ResultReqClientSender extends CustomJMSSender {

    private static final ResultReqClientSender INSTANCE = new ResultReqClientSender();

    private ResultReqClientSender() {
        super(FilesJMS.RETOUR_REQ_CLIENT);
    }

    public static ResultReqClientSender getInstance() {
        return INSTANCE;
    }

}
