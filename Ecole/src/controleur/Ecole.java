/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;
import java.sql.SQLException;
import modele.*;

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
        try {
            connexion conn = new connexion();
            System.out.println(conn.remplirChampsRequete("select * from personne").get(0));
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
    
}
