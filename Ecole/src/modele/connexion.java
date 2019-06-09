/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/*
 * 
 * Librairies importees
 */
import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * 
 * @author marko
 */
public class connexion {

    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    private Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    /**
     * ArrayList public pour les tables
     */
    public ArrayList<String> tables = new ArrayList<>();
    /**
     * ArrayList public pour les requetes de selection
     */
    public ArrayList<String> requetes = new ArrayList<>();
    /**
     * ArrayList public pour les requetes de MAJ
     */
    public ArrayList<String> requetesMaj = new ArrayList<>();

    /**
     * Constructeur avec 3 parametres : nom, login et password de la BDD locale
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public connexion() throws SQLException, ClassNotFoundException {
        
        Class.forName("com.mysql.jdbc.Driver");
        
        // url de connexion "jdbc:mysql://localhost:306/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost:3306/Ecole";

        //creation d'une connexion JDBC a la base 
        conn = DriverManager.getConnection(urlDatabase, "root", "");

        // creation d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }

    /**
     * Constructeur avec 4 parametres : username et password ECE, login et
     * password de la BDD a distance sur le serveur de l'ECE
     * @param usernameECE
     * @param passwordECE
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    
    /**
     * Methode qui ajoute la table en parametre dans son ArrayList
     *
     * @param table
     */
    public void ajouterTable(String table) {
        tables.add(table);
    }

    /**
     * Methode qui ajoute la requete de selection en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * Methode qui ajoute la requete de MAJ en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }
    
    //Executer une requete en paramètre
    public void executerRequete(String req) throws SQLException {
        // recuperation de l'ordre de la requete
        executeUpdate(req);
    }
    /**
     * Methode qui retourne l'ArrayList des champs de la table en parametre
     *
     * @param table
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsTable(String table) throws SQLException {
        // recuperation de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        // recuperation du resultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        // ajouter un "\n" a la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * Methode qui retourne l'ArrayList des champs de la requete en parametre
     * @param requete
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsRequete(String requete) throws SQLException {
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // rÃ©cupÃ©ration du resultat de l'ordre
        rsetMeta = rset.getMetaData();

       // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste = new ArrayList<>();

        // tant qu'il reste une ligne 
        while (rset.next()) {
            String champs;
            
            for(int j = 0; j < nbColonne; j++) {
                champs = rset.getString(j);

                // ajouter les champs de la ligne dans l'ArrayList
                liste.add(champs);
            }
        }

        // Retourner l'ArrayList
        return liste;
    }
    
    public ArrayList remplirChampsRequete(String requete, String[] champsvoulu) throws SQLException {
        // rÃ©cupÃ©ration de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // rÃ©cupÃ©ration du resultat de l'ordre
        rsetMeta = rset.getMetaData();

        // creation d'une ArrayList de String
        ArrayList<String> liste = new ArrayList<>();

        // tant qu'il reste une ligne 
        while (rset.next()) {
            String champs;
            
            for(int j = 0; j < champsvoulu.length; j++) {
                champs = rset.getString(champsvoulu[j]); // ajouter premier champ

                // ajouter les champs de la ligne dans l'ArrayList
                liste.add(champs);
            }
        }

        // Retourner l'ArrayList
        return liste;
    }
    
    //Trouver un objet par l'id dans sa table
    public ArrayList find(String table, int id, String[] champsvoulu) throws SQLException{
        ArrayList<String> data = new ArrayList();
        if(id == -1){
            data.addAll(remplirChampsRequete("select * from "+ table, champsvoulu) );
        }else{
            data.addAll(remplirChampsRequete("select * from "+ table + " WHERE id = "+ id, champsvoulu) );
        }
        return data;
    }

    /**
     * Methode qui execute une requete de MAJ en parametre
     * @param requeteMaj
     * @throws java.sql.SQLException
     */
    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }
}
