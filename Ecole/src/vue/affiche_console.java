/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.sql.SQLException;
import java.util.ArrayList;
import modele.*;
import java.util.Scanner;

/**
 *
 * @author marko
 */
public class affiche_console {
    
    //Test
    //evaluation eval = new evaluation("interrogation",13,1,1,1,1);
   
    Scanner sc = new Scanner(System.in);
    
    boolean fin = false;
    int choix = -1;
    
    public void menu() throws SQLException, ClassNotFoundException{
        int cpt = 0;
        while(!this.fin){
            cpt++;
            System.out.println("\n");
            System.out.println("***********************************************"+cpt);
            System.out.println("Menu principale de l'école:");
            System.out.println("    1) Afficher la liste des niveaux et des classes");
            System.out.println("    2) Afficher la liste des élèves");
            System.out.println("    3) Afficher la liste des enseignements");
            System.out.println("    10) Quitter \n");
            this.choix = sc.nextInt();
            
            if(choix == 1){ //gestion des niveaux et des classes
                menu_niveau();
            }
            if(choix == 2){ //gestion des élèves
                menu_eleve();
            }
            if(choix == 3){ // gestion des enseignements
                menu_enseignement();
            }
            if(choix == 10){ //Quitter
                this.fin = true;
            }
            if( choix != 3 && choix !=2 && choix != 1 && choix !=-1 && choix != 10){
                System.out.println(choix +" n'est pas valide.");
                System.out.println("Veuillez saisir un choix valide.");
            }
            choix = -1;
        }
        System.out.println("Fin de la gestion.");
        System.out.println("***********************************************"+cpt);
    }
    
    public void menu_classe() throws SQLException, ClassNotFoundException{
        connexion conn = new connexion();
        String[] champvoulu = {"nom"};
        ArrayList<String> listeclasse = new ArrayList();
        listeclasse.addAll(conn.find("classe", -1, champvoulu));
        if(!listeclasse.isEmpty())
        {
            System.out.println("Liste des classes :");
            for(int i = 0; i < listeclasse.size() ; i++){
                System.out.println("    - "+ listeclasse.get(i));
            }
        }else{
            System.out.println("Il n'y a aucune classe.");
        }
    }
    
    public void menu_niveau() throws SQLException, ClassNotFoundException{
        connexion conn = new connexion();
        
        //liste des niveaux
        String[] champvoulu = {"id","nom"};
        ArrayList<String> listeniveau = new ArrayList();
        listeniveau.addAll(conn.find("niveau", -1, champvoulu));
        
        //liste des classes
        ArrayList<String> listeclasse = new ArrayList();
        String[] champvoulu1 = {"nom","fk_niveau"};
        listeclasse.addAll(conn.find("classe",-1, champvoulu1));
        
        if(!listeniveau.isEmpty())
        {
            System.out.println("Liste des niveaux et des classes :");
            for(int i = 0; i < listeniveau.size()/2 ; i++){
                for(int j = 0; j < listeclasse.size()/2 ; j++){
                    //Matching entre niveau et classe avec key et fk
                    if(listeniveau.get(2*i).equals(listeclasse.get(2*j+1)) ){
                        System.out.println("    - "+ listeniveau.get(2*i+1) + " " + listeclasse.get(2*j));
                    }
                }
            }
        }else{
            System.out.println("Il n'y a aucun niveau.");
        }
    }
    
    public void menu_enseignement(){
        enseignement ens = new enseignement(-1);
        ens.affiche();
    }
    
    public String[] menu_eleve() throws SQLException, ClassNotFoundException{
        String[] eleve = null;
        boolean fin1 = false;
        while(!fin1){
            String nom;
            int choixmenu;
            connexion conn = new connexion();
            String[] champvoulu = {"nom", "prenom"};
            ArrayList<String> listeeleve = new ArrayList();
            listeeleve.addAll(conn.find("personne", -1, champvoulu));
            eleve = new String[listeeleve.size()/2];
            if(!listeeleve.isEmpty())
            {
                System.out.println("Liste des élèves :  (Format : (Nom) (Prénom))");
                for(int i = 0; i < listeeleve.size()/2 ; i++){
                    System.out.println("    - "+ listeeleve.get(2*i) + " " + listeeleve.get(2*i + 1));
                    eleve[i/2] = listeeleve.get(2*i) + listeeleve.get(2*i + 1);
                }
            }else{
                System.out.println("Il n'y a aucun élève.");
            }
            System.out.println("Entrez 'fin' pour faire un retour.");
            System.out.print("Veuillez entrer le nom d'un élève à sélectionner : ");
            nom = sc.next();
            int id_result = -1;
            for(int i = 0; i < listeeleve.size()/2 ; i++){
                if( nom.equals(listeeleve.get(2*i)) ){
                    id_result =i;
                }
            }
            
            if(nom.equals("fin")){
               fin1 = true;
            }
            
            if(id_result== -1 && id_result!=0){
                System.out.println("L'élève n'a pas été trouvé ou n'existe pas.");
            }
            else{ //L'élève a ete trouvé
                boolean fin2 = false;
                while(!fin2){
                    if(id_result != -1){//Si l'élève est trouvé
                        System.out.println("    1) Afficher le bulletin");
                        System.out.println("    2) Afficher la liste des notes");
                        System.out.println("    3) Quitter");
                        choixmenu = sc.nextInt();
                        if(choixmenu == 1){ //Affichage du bulletin
                            
                            menubulletin(listeeleve.get(2*id_result), listeeleve.get(2*id_result+1) );
                        }
                        if(choixmenu == 2){ //Affichage de la liste des notes
                            menunote(listeeleve.get(2*id_result), listeeleve.get(2*id_result+1));
                        }
                        if(choixmenu == 3){ //Quitter
                            fin2 = true;
                        }
                        if(choixmenu != 1 && choixmenu != 2 && choixmenu != 3){
                            System.out.println("Choix invalide.");
                        }
                    }
                }
            }
        }
        return eleve;
    }
    
    public void menubulletin(String nom, String prenom) throws SQLException, ClassNotFoundException{
        connexion conn = new connexion();
        ArrayList<String> infoeleve = new ArrayList();
        ArrayList<String> listeeval = new ArrayList();
        ArrayList<String> listebulletin = new ArrayList();
        ArrayList<String> listedetailbulletin = new ArrayList();
        ArrayList<String> listediscipline = new ArrayList();
        
        
        //Chargement de toutes les informations nécessaires pour afficher le bulletin
        
        // 1) Récuperer l'id et le niveau de l'eleve en parametre
        String req = "select id, fk_niveau FROM personne WHERE nom ='"+nom+"' AND prenom ='"+prenom+"'";
        String[] champvoulu = {"id", "fk_niveau"};
        infoeleve.addAll(conn.remplirChampsRequete(req, champvoulu ));
        
        // 2) Récuperer la liste d'évaluation {id, note, type, fk_discipline} de l'eleve en parametre
        req = "select id, note, type, fk_discipline FROM evaluation WHERE fk_personne ='"+infoeleve.get(0) + "' ORDER BY fk_discipline";
        String[] champvoulu1 = {"id", "note", "type", "fk_discipline"};
        listeeval.addAll(conn.remplirChampsRequete(req, champvoulu1));
        
        // 3) Récuperer la liste des bulletins {id, fk_personne, fk_trimestre} de l'eleve en parametre
        req = "select * FROM bulletin WHERE fk_personne ='"+infoeleve.get(0)+"'";
        String[] champvoulu2 = {"id", "fk_personne", "fk_trimestre"};
        listebulletin.addAll(conn.remplirChampsRequete(req, champvoulu2));
        
        // 4) Récuperer la liste des disciplines {id, nom} de l'eleve en parametre
        String[] champvoulu5 = {"id", "nom"};
        req = "select DISTINCT * FROM discipline WHERE fk_enseignement ='"+ infoeleve.get(1) + "' ORDER BY id";
        listediscipline.addAll(conn.remplirChampsRequete(req, champvoulu5));
        
        // 4) Récuperer la liste des disciplines {id, nom} de l'eleve en parametre
        String[] champvoulu6 = {"id", "fk_bulletin", "fk_evaluation"};
        for(int i = 0; i < listeeval.size(); i++){
            req = "select * FROM detailbulletin WHERE fk_evaluation ='"+ listeeval.get(i) + "' ORDER BY fk_bulletin";
            listedetailbulletin.addAll(conn.remplirChampsRequete(req, champvoulu6));
        }
        //Affichage du bulletin
        for(int i = 0; i < listebulletin.size()/3; i++){
            double moy = 0.0;
            System.out.println("Bulletin n°"+listebulletin.get(3*i) +" de "+ nom+ " "+prenom);

            for(int j = 0; j< listediscipline.size()/2; j++){
                double sommenote = 0.0;
                int cpt = 0;

                for(int k=0; k< listeeval.size()/4; k++){
                    //if( Integer.parseInt(listedetailbulletin.get(3*k+1)) == Integer.parseInt(listebulletin.get(3*i))){
                        if( Integer.parseInt(listeeval.get(4*k+3)) == Integer.parseInt(listediscipline.get(2*j))
                                && (Integer.parseInt(listedetailbulletin.get(3*k+1)) == Integer.parseInt(listebulletin.get(3*i)) ) ){
                            sommenote = sommenote + Integer.parseInt(listeeval.get(4*k+1));
                            cpt++;
                        }
                    //}
                }

                if(cpt != 0){
                    System.out.println(" - "+listediscipline.get(2*j+1) +"   "+ sommenote/cpt+ "/20");
                    moy = moy + sommenote/cpt;
                }
                if(cpt == 0){
                    System.out.println(" - "+listediscipline.get(2*j+1) +"   "+ sommenote+ "/20");
                    moy = moy + sommenote;
                }
            }
            if(listediscipline.size()/2 != 0)
                System.out.println("Moyenne Générale sur le semestre : " + moy/ (listediscipline.size()/2) +"/20");
            if(listediscipline.size()/2 == 0)
                System.out.println("Moyenne Générale sur le semestre : " + moy +"/20");
            System.out.println("***");
        }
    }
    
    public void menunote(String nom, String prenom) throws SQLException, ClassNotFoundException{
        connexion conn = new connexion();
        ArrayList<String> infoeleve = new ArrayList();
        ArrayList<String> listeeval = new ArrayList();
        ArrayList<String> listebulletin = new ArrayList();
        ArrayList<String> listediscipline = new ArrayList();
        
        
        //Chargement de toutes les informations nécessaires pour afficher le bulletin
        
        // 1) Récuperer l'id et le niveau de l'eleve en parametre
        String req = "select id, fk_niveau FROM personne WHERE nom ='"+nom+"' AND prenom ='"+prenom+"'";
        String[] champvoulu = {"id", "fk_niveau"};
        infoeleve.addAll(conn.remplirChampsRequete(req, champvoulu ));
        
        // 2) Récuperer la liste d'évaluation {id, note, type, fk_discipline} de l'eleve en parametre
        req = "select id, note, type, fk_discipline FROM evaluation WHERE fk_personne ='"+infoeleve.get(0) + "' ORDER BY fk_discipline";
        String[] champvoulu1 = {"id", "note", "type", "fk_discipline"};
        listeeval.addAll(conn.remplirChampsRequete(req, champvoulu1));
        
        // 3) Récuperer la liste des bulletins {id, fk_personne, fk_trimestre} de l'eleve en parametre
        req = "select * FROM bulletin WHERE fk_personne ='"+infoeleve.get(0)+"'";
        String[] champvoulu2 = {"id", "fk_personne", "fk_trimestre"};
        listebulletin.addAll(conn.remplirChampsRequete(req, champvoulu2));
        
        // 4) Récuperer la liste des disciplines {id, nom} de l'eleve en parametre
        String[] champvoulu5 = {"id", "nom"};
        req = "select DISTINCT * FROM discipline WHERE fk_enseignement ='"+ infoeleve.get(1) + "'";
        listediscipline.addAll(conn.remplirChampsRequete(req, champvoulu5));
        
        //Affichage du releve
        for(int i = 0; i < listebulletin.size()/3; i++){
            double moy = 0.0;
            System.out.println("Relevé de note");

            for(int j = 0; j< listediscipline.size()/2; j++){
                System.out.println(listediscipline.get(2*j+1) + " :");
                if(!listeeval.isEmpty()){
                    for(int k=0; k< listeeval.size()/4; k++){
                        if( Integer.parseInt(listeeval.get(4*k+3)) == Integer.parseInt(listediscipline.get(2*j)) ){
                        System.out.println("    - "+listeeval.get(4*k+2) +"   "+ listeeval.get(4*k+1)+"/20");
                        }
                    }
                } else{
                    System.out.println("    Aucune note.");
                }
            }
            System.out.println("***");
        }
    }
}
