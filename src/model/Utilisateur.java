package model;

/* Sources :
https://drive.google.com/file/d/10Qu73GKToaf7oOndfdIq0Zd4hZA8Z7Uc/view
https://drive.google.com/file/d/1QZy3Q5R-kIY697UORIlo7DmL8ZY_Kcwo/view
https://drive.google.com/file/d/1jUEKNnXz6l-_FBw6AGSPawFFzJeSDmTS/view
*/

/**
 * Classe abstraite représentant un utilisateur de base avec des informations personnelles
 */

public abstract class Utilisateur {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse;

    /**
     * Constructeur de la classe Utilisateur.
     *
     * @param id L'identifiant de l'utilisateur.
     * @param nom Le nom de l'utilisateur.
     * @param prenom Le prénom de l'utilisateur.
     * @param email L'email de l'utilisateur.
     * @param motDePasse Le mot de passe de l'utilisateur.
     */
    public Utilisateur(int id, String nom, String prenom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }


    /**
     * Retourne l'identifiant de l'utilisateur
     * @return L'identifiant de l'utilisateur
     */
    public int getId() { return id; }

    /**
     * Retourne le nom de l'utilisateur
     * @return Le nom de l'utilisateur
     */
    public String getNom() { return nom; }


    /**
     * Retourne le prenom de l'utilisateur
     * @return Le prénom de l'utilisateur
     */
    public String getPrenom() { return prenom; }

    /**
     * Retourne l'email de l'utilisateur
     * @return L'email de l'utilisateur
     */
    public String getEmail() { return email; }

    /**
     * Retourne le mot de passe de l'utilisateur
     * @return Le mot de passe de l'utilisateur
     */
    public String getMotDePasse() { return motDePasse; }


    /**
     * On enregistre l'identifiant de l'utilisateur
     * @param id identifiant de l'utilisateur
     */
    public void setId(int id) { this.id = id; }


    /**
     * On enregistre le nom de l'utilisateur
     * @param nom Le nom de l'utilisateur
     */
    public void setNom(String nom) { this.nom = nom; }

    /**
     * On enregistre le prenom de l'utilisateur
     * @param prenom Le prénom de l'utilisateur
     */
    public void setPrenom(String prenom) { this.prenom = prenom; }

    /**
     * On enregistre l'email de l'utilisateur
     * @param email L'email de l'utilisateur
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * On enregistre le mot de passe de l'utilisateur
     * @param motDePasse Le mot de passe de l'utilisateur
     */
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    /**@return Une chaîne contenant le nom, le prénom et l'email de l'utilisateur.*/
    @Override
    public String toString() {
        return nom + " " + prenom + " (" + email + ")";
    }
}
