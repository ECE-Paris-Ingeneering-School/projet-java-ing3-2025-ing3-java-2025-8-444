package model;

/**
 * Classe représentant un administrateur
 * Un administrateur est un utilisateur avec des droits de gestion supplémentaires.
 *
 */

public class Admin extends Utilisateur {

    /**
     * Constructeur pour créer un administrateur.
     *
     * @param id Identifiant
     * @param nom Nom de l'administrateur
     * @param prenom Prénom de l'administrateur
     * @param email Email
     * @param motDePasse Mot de passe
     */
    public Admin(int id, String nom, String prenom, String email, String motDePasse) {
        super(id, nom, prenom, email, motDePasse);
    }

}
