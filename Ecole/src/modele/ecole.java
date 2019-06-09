/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marko
 */
public final class ecole {
    //Attributs
    private final ArrayList<niveau> listeniveau = new ArrayList();
    private String nom;
    
    //Constructeurs
    public ecole() {   
        super();
        charger_ecole();
    }
    
    public ecole(String nom) {   
        super();
        this.nom = nom;
        charger_ecole();
    }
    
    
    //Methodes
    public void charger_ecole(){
        //Récupération des infos contenu dans la table niveau
        try {
            ArrayList<String> detail1 = new ArrayList<>();
            ArrayList<String> detail2 = new ArrayList<>();
            connexion conn = new connexion();
            String[] champsvoulu1 = {"id"};
            detail1.addAll(conn.remplirChampsRequete("select * from niveau", champsvoulu1));
            String[] champsvoulu2 = {"id", "nom"};
            detail2.addAll(conn.remplirChampsRequete("select * from niveau", champsvoulu2));
            for(int i = 0; i < detail1.size(); i++){
                int temp = Integer.parseInt(detail2.get(2*i));
                niveau n = new niveau(temp, detail2.get(2*i+1));                
                this.listeniveau.add(n);
            }
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
    
    public void affiche() {
        if(!this.listeniveau.isEmpty()){
            System.out.println("Il y a "+ this.listeniveau.size() + " niveau(x).");
            for(int j = 0; j < this.listeniveau.size(); j++){
                System.out.println("- " + this.listeniveau.get(j).getnom());
            }
        }
        else{
            System.out.println("Il n'y a pas de classe.");
        }
        System.out.println("\n");
    }
}
