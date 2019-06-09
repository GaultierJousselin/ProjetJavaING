/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;
import java.sql.SQLException;
import modele.*;
import vue.*;

/**
 *
 * @author marko
 */
public class Ecole {
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        
        //affiche_console aff = new affiche_console();
        new MainMenuTest().setVisible(true);
        //aff.menu();
        
    }
    
}
