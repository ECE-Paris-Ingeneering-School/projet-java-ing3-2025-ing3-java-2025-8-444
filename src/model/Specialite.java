package model;

/* Sources :
https://drive.google.com/file/d/10Qu73GKToaf7oOndfdIq0Zd4hZA8Z7Uc/view
https://drive.google.com/file/d/1QZy3Q5R-kIY697UORIlo7DmL8ZY_Kcwo/view
https://drive.google.com/file/d/1jUEKNnXz6l-_FBw6AGSPawFFzJeSDmTS/view
*/


/**
 * Représente une spécialité avec un nom et une description.
 */

public class Specialite {
    private String nom;
    private String description;

    /**
     * Constructeur de la classe Specialite.
     *
     * @param nom Le nom de la spécialité.
     * @param description La description de la spécialité.
     */
    public Specialite(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**@return Le nom de la specialite*/
    public String getNom() { return nom; }

    /**@return La description de la specialite*/
    public String getDescription() { return description; }

    /**@param nom Le nom de la specialite*/
    public void setNom(String nom) { this.nom = nom; }


    /**@param description La descprition de la specialite*/
    public void setDescription(String description) { this.description = description; }


    /**@return Le nom de la spécialité.*/
    @Override
    public String toString() {
        return nom;
    }
}