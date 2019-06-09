/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author marko
 */
public final class personne extends JFrame {
    //Attributs
    private String nom;
    private String prenom;
    private String type;
    private int id_classe;
    
    public ArrayList<String>detail = new ArrayList();
    
    //Constructeurs
    public personne() {   
        super();
    }
    
    public personne(String nom, String prenom, String type, int id_classe) {   
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.type = type;
        this.id_classe = id_classe;
    }
    
    
    //Getters and Setters
    public String getnom(){
        return this.nom;
    }
    
    public String getprenom(){
        return this.prenom;
    }
    
    public String gettype(){
        return this.type;
    }
    
    public int getidclasse(){
        return this.id_classe;
    }
    
    
    //Methodes
    
    public void charger_personne(){
         //Récupération des infos contenu dans la table Classe
        try {
            connexion conn = new connexion();
            String[] champsvoulu = {"nom", "prenom", "type", "fk_classe"};
            this.detail.addAll(conn.remplirChampsRequete("select * from personne WHERE fk_classe = "+ this.id_classe, champsvoulu));
            this.nom = this.detail.get(0);
            this.prenom = this.detail.get(1);
            this.type = this.detail.get(2);
            this.id_classe = Integer.parseInt(this.detail.get(3));
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
    
    public void affiche() {
        System.out.println("        Nom : " + this.nom + " " + this.prenom);
        System.out.println("        Type : "+ this.type + "\n");
        
    }
    
}
