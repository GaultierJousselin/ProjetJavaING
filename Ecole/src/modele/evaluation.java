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
public class evaluation {
    //Attributs
    private int note;
    private String nom;
    private int fk_discipline;
    private int id_evaluation;
    private int fk_detailbulletin;
    private int fk_personne;
    
    public ArrayList<String>detail = new ArrayList();
    
    //Constructeurs
    public evaluation() {   
        charger_evaluation();
    }
    
    public evaluation(String nom, int note, int fk_discipline, int id_eval, int fk_detailbulletin, int fk_personne ) {   
        this.nom = nom;
        this.note = note;
        this.fk_detailbulletin = fk_detailbulletin;
        this.fk_discipline = fk_discipline;
        this.fk_personne = fk_personne;
        this.id_evaluation = id_eval;
        charger_evaluation();
    }
    
    
    //Getters and Setters
    public String getnom(){
        return this.nom;
    }
    
    public int getnote(){
        return this.note;
    }
    
    public int getidpers(){
        return this.fk_personne;
    }
    
    public int getiddiscipline(){
        return this.fk_discipline;
    }
    
    public int getidpersonne(){
        return this.note;
    }
    
    
    
    //Methodes
    
    public void charger_evaluation(){
         //Récupération des infos contenu dans la table Classe
        try {
            connexion conn = new connexion();
            String[] champsvoulu = {"id","note", "type", "fk_discipline", "fk_detailbulletin"};
            this.detail.addAll(conn.remplirChampsRequete("select * from evaluation WHERE fk_personne = " + this.fk_personne, champsvoulu));
            this.id_evaluation = Integer.parseInt(this.detail.get(0));
            this.note = Integer.parseInt(this.detail.get(1));  
            this.nom = this.detail.get(2);
            this.fk_discipline = Integer.parseInt(this.detail.get(3));
            this.fk_detailbulletin = Integer.parseInt(this.detail.get(4));
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
    
    public void affiche() throws SQLException {
        String discipline = "";
        try {
            connexion conn = new connexion();
            String[] champsvoulu1 ={"nom"};
            ArrayList<String> data = new ArrayList();
            data.addAll(conn.find("discipline", this.fk_discipline, champsvoulu1) );
            discipline = data.get(0);
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        System.out.println("        Discipline : " + discipline+ " " + this.nom +"    note : "+ this.note);
    }
}
