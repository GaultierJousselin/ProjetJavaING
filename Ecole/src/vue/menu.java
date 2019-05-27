/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author marko
 */
class MenuActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

public class menu extends java.awt.MenuBar {
    public MenuItem menuDiscipline, menuNouveau,menuClasse;
    
    public menu() {
        Menu menuEcole = new Menu("Ecole");
        Menu menuEleve = new Menu("Eleve");
        menuClasse = new MenuItem("Classe");
        
        menuDiscipline = new MenuItem("Discipline");        
        menuDiscipline.addActionListener(new MenuActionListener());
        
        menuNouveau = new MenuItem("Nouveau");
        menuNouveau.addActionListener(new MenuActionListener());
        
        menuEcole.add(menuClasse);
        menuEleve.add(menuDiscipline);
        
        menuEcole.addSeparator();
        
        menuEcole.add(menuNouveau);
        
        add(menuEcole);
        add(menuEleve);
    }
    
}