/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;



/**
 *
 * @author marko
 */
public final class classe extends JFrame {
    //Attributs
    public ArrayList<personne> listepersonne = new ArrayList();
    public ArrayList<String> listeclasse = new ArrayList();
    private int id_classe;
    private String nom;
    
    //Constructeurs
    public classe() {   
        super();
        charger_classe();
    }
    
    public classe(int id_classe, String nom) {   
        super();
        this.id_classe = id_classe;
        this.nom = nom;
        charger_classe();
    }
    
    /* 
     * Getters & Setters
     */
    
    public ArrayList<personne> getlistepersonne() {
        return this.listepersonne;
    }
    
    public String getnom(){
        return this.nom;
    }
    
    public int getidclasse(){
        return this.id_classe;
    }
    
    
    //Methodes
    
    public void charger_classe(){
         //Récupération des infos contenu dans la table Classe
        try {
            ArrayList<String> detail = new ArrayList<>();
            connexion conn = new connexion();
            //Chargement de la liste des personnes de la classe
            String[] champsvoulu1 = {"id"};
            this.listeclasse.addAll(conn.remplirChampsRequete("select * from personne WHERE fk_classe = " + this.id_classe, champsvoulu1));
            String[] champsvoulu2 = {"nom", "prenom", "type"};
            for(int i =0; i < this.listeclasse.size(); i++){
                detail.addAll(conn.remplirChampsRequete("select * from personne WHERE fk_classe = " + this.id_classe, champsvoulu2));
                personne p = new personne(detail.get(3*i) , detail.get(3*i+1), detail.get(3*i+2), this.id_classe );                
                this.listepersonne.add(p);
            }      
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
    
    public void affiche() {
        if(!this.listepersonne.isEmpty()){
            System.out.println("    Il y a "+ this.listepersonne.size()+ " personne(s) dans cette classe.");
            System.out.println("    Voici la liste :");
            for(int i = 0; i < this.listepersonne.size(); i++){
                this.listepersonne.get(i).affiche();
            }
        }else{
            System.out.println("Il n'y a personne dans cette classe.");
        }
    }
}
