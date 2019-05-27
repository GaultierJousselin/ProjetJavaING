/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.*;

/**
 *
 * @author marko
 */
public class menu extends java.awt.MenuBar {
    public MenuItem menuDiscipline, menuNouveau,menuClasse;
    
    public menu() {
        Menu menuEcole = new Menu("Ecole");
        Menu menuEleve = new Menu("Eleve");
        menuClasse = new MenuItem("Classe");
        menuDiscipline = new MenuItem("Discipline");
        menuNouveau = new MenuItem("Nouveau");
        
        menuEcole.add(menuClasse);
        menuEleve.add(menuDiscipline);
        
        menuEcole.addSeparator();
        
        menuEcole.add(menuNouveau);
        
        add(menuEcole);
        add(menuEleve);
    }
    
}
