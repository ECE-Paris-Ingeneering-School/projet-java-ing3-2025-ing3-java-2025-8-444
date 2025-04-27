package model;

/* Sources :
https://drive.google.com/file/d/10Qu73GKToaf7oOndfdIq0Zd4hZA8Z7Uc/view
https://drive.google.com/file/d/1QZy3Q5R-kIY697UORIlo7DmL8ZY_Kcwo/view
https://drive.google.com/file/d/1jUEKNnXz6l-_FBw6AGSPawFFzJeSDmTS/view
*/

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
