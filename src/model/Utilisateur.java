package model;

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


    /**@return L'identifiant de l'utilisateur*/
    public int getId() { return id; }
    /**@return Le nom de l'utilisateur*/
    public String getNom() { return nom; }
    /**@return Le prénom de l'utilisateur*/
    public String getPrenom() { return prenom; }
    /**@return L'email de l'utilisateur*/
    public String getEmail() { return email; }
    /**@return Le mot de passe de l'utilisateur*/
    public String getMotDePasse() { return motDePasse; }


    /**@param id identifiant de l'utilisateur*/
    public void setId(int id) { this.id = id; }
    /**@param nom Le nom de l'utilisateur*/
    public void setNom(String nom) { this.nom = nom; }
    /**@param prenom Le prénom de l'utilisateur*/
    public void setPrenom(String prenom) { this.prenom = prenom; }
    /**@param email L'email de l'utilisateur*/
    public void setEmail(String email) { this.email = email; }
    /**@param motDePasse Le mot de passe de l'utilisateur*/
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    /**@return Une chaîne contenant le nom, le prénom et l'email de l'utilisateur.*/
    @Override
    public String toString() {
        return nom + " " + prenom + " (" + email + ")";
    }
}
