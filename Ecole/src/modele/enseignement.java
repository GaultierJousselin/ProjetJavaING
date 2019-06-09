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
public final class enseignement {
    //Attributs
    public ArrayList<discipline> listediscipline = new ArrayList();
    private int fk_niveau;
    
    //Constructeurs
    public enseignement() {   
        super();
        charger_enseignement();
    }
    
    public enseignement(int id) {   
        super();
        this.fk_niveau = id;
        charger_enseignement();
    }
    
    /* 
     * Getters & Setters
     */
    
    public ArrayList<discipline> getlistediscipline() {
        return this.listediscipline;
    }
    
    public void setfkniveau(int id){
        this.fk_niveau = id;
    }
    
    
    //Methodes
    public void charger_enseignement(){
         //Récupération des infos contenu dans la table Classe
        try {
            ArrayList<String> detail1 = new ArrayList<>();
            ArrayList<String> detail2 = new ArrayList<>();
            connexion conn = new connexion();
            //Récuperer le nombre de discipline de l'enseignement
            if(this.fk_niveau == -1) {
                String[] champsvoulu1 = {"nom"};
                detail1.addAll(conn.remplirChampsRequete("select * from discipline", champsvoulu1));
                 for(int j = 0; j < detail1.size(); j++){
                    discipline d = new discipline(detail1.get(j));
                    this.listediscipline.add(d);
                }
            } else {
                String[] champsvoulu1 = {"id"};
                detail1.addAll(conn.remplirChampsRequete("select * from discipline WHERE fk_enseignement = " + this.fk_niveau, champsvoulu1));
                String[] champsvoulu2 = {"nom"};
                detail2.addAll(conn.remplirChampsRequete("select * from discipline WHERE fk_enseignement = " + this.fk_niveau, champsvoulu2));
                //Récuperer la liste des discisplines
                for(int i = 0; i < detail1.size(); i++){
                    discipline d = new discipline(detail2.get(i));
                    this.listediscipline.add(d);
                }    
            }
                    
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
    
    public void affiche() {
        if(!this.listediscipline.isEmpty()){
            System.out.println("    Il y a "+ this.listediscipline.size()+ " discipline(s).");
            System.out.println("    Voici la liste :");
            
            for(int i = 0; i < this.listediscipline.size(); i++){
                System.out.println("  - " + this.listediscipline.get(i).getnom() );
            }
        }else{
            System.out.println("Il n'y a pas de discipline pour ce niveau.");
        }
    }
}
