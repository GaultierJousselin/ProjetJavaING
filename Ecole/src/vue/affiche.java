/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.*;

/**
 *
 * @author marko
 */
public class affiche extends JFrame{
    
	public affiche(){
            super();
            setTitle("Gestion d'une école");
            setSize(800, 570);
            menu mf = new menu();
            setMenuBar(mf);
            
        }
}