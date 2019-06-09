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
public final class niveau {
    //Attributs
    private final ArrayList<classe> listeclasse = new ArrayList();
    private enseignement ens;
    private String nom;
    private int id_niveau;
    
    //Constructeurs
    public niveau() {   
        super();
        this.ens = new enseignement(-1);
        //charger_niveau();
    }
    
    public niveau(int id, String nom) {   
        super();
        this.id_niveau = id;
        this.ens = new enseignement(id);
        this.nom = nom;
        //charger_niveau();
    }
    
    //Getters & Setters
    public String getnom(){
        return this.nom;
    }
    
    
    //Methodes
    public void charger_niveau(){
        //Chargement des infos contenu dans la table niveau
        try {
            ArrayList<String> detail1 = new ArrayList<>();
            ArrayList<String> detail2 = new ArrayList<>();
            connexion conn = new connexion();
            String[] champsvoulu1 = {"id"};
            //Chargement de la liste des classes
            detail1.addAll(conn.remplirChampsRequete("select * from classe WHERE fk_niveau = " + this.id_niveau, champsvoulu1));
            String[] champsvoulu2 = {"id", "nom"};
            detail2.addAll(conn.remplirChampsRequete("select * from classe WHERE fk_niveau = " + this.id_niveau, champsvoulu2));
            for(int i = 0; i < detail1.size(); i++){
                int temp = Integer.parseInt(detail2.get(2*i));
                classe c = new classe(temp, detail2.get(2*i+1));                
                this.listeclasse.add(c);
            }
            //Chargement de l'enseignement(liste des disciplines)
            //this.ens.charger_enseignement();
            
        }catch(ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
    }
    
    public void affiche() {
        if(!this.listeclasse.isEmpty()){
            System.out.println("Il y a "+ this.listeclasse.size() + " classe(s) au niveau " + this.nom +".");
            for(int j = 0; j < this.listeclasse.size(); j++){
                System.out.println("    Classe n°"+(j+1)+ ":");
                this.listeclasse.get(j).affiche();
            }
        }
        else{
            System.out.println("Il n'y a pas de classe.");
        }
    }
}
