package model;

/**
 * Classe représentant un lieu où se déroulent des rendez-vous
 * Le lieu contient l'adresse, la ville et le code postal associés.
 *
 */

public class Lieu {
    private int id;
    private String nom;
    private String adresse;
    private String ville;
    private String codePostal;

    /**
     * Constructeur pour créer un lieu.
     *
     * @param id Identifiant unique
     * @param nom Nom du lieu
     * @param adresse Adresse complète
     * @param ville Ville
     * @param codePostal Code postal
     */
    public Lieu(int id, String nom, String adresse, String ville, String codePostal) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    /**
     * Récupère l'identifiant du lieu.
     * @return l'identifiant du lieu
     */
    public int getId() { return id; }

    /**
     * Récupère le nom du lieu.
     * @return le nom du lieu
     */
    public String getNom() { return nom; }

    /**
     * Récupère l'adresse du lieu.
     * @return l'adresse du lieu
     */
    public String getAdresse() { return adresse; }

    /**
     * Récupère la ville du lieu.
     * @return la ville du lieu
     */
    public String getVille() { return ville; }

    /**
     * Récupère le code postal du lieu.
     * @return le code postal du lieu
     */
    public String getCodePostal() { return codePostal; }

    /**
     * Définit l'identifiant du lieu.
     * @param id l'identifiant du lieu à définir
     */
    public void setId(int id) { this.id = id; }

    /**
     * Définit le nom du lieu.
     * @param nom le nom du lieu à définir
     */
    public void setNom(String nom) { this.nom = nom; }

    /**
     * Définit l'adresse du lieu.
     * @param adresse l'adresse du lieu à définir
     */
    public void setAdresse(String adresse) { this.adresse = adresse; }

    /**
     * Définit la ville du lieu.
     * @param ville la ville du lieu à définir
     */
    public void setVille(String ville) { this.ville = ville; }

    /**
     * Définit le code postal du lieu.
     * @param codePostal le code postal du lieu à définir
     */
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

}
