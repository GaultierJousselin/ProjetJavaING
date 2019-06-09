package modele;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marko
 */
public class discipline {
     //Attributs
    private String nom;
    private int id_discipline;
    public ArrayList<String>detail = new ArrayList();
    
    //Constructeurs
    public discipline() {   
        super();
    }
    
    public discipline(String nom) {   
        super();
        this.nom = nom;
    }
    
    
    //Getters and Setters
    public String getnom(){
        return this.nom;
    }
    
     public int getid(){
        return this.id_discipline;
    }
    
    //Methodes
    
    public void charger_discipline(){
         //Récupération des infos contenu dans la table Classe
        try {
            connexion conn = new connexion();
            String[] champsvoulu = {"nom"};
            String requete = "select * from classe WHERE id = "+this.id_discipline;
            this.detail.addAll(conn.remplirChampsRequete(requete , champsvoulu));
            this.nom = this.detail.get(0);
            
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
    
    public void affiche() {
        System.out.println("        Discipline : " + this.nom);
    } 
}
