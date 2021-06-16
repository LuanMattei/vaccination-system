/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.iot.systemvac.TelaLogin;
import javax.swing.JFrame;

/**
 *
 * @author Desktop
 */
public class Logout {
    public static void logOut(JFrame context, TelaLogin loginScreen){
        LoginSession.isLoggedIn = false;
        context.setVisible(false);
        loginScreen.setVisible(true);
    }
}
