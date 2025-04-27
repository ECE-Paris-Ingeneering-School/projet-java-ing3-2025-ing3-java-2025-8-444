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


    /** @return l'identifiant du lieu */
    public int getId() { return id; }

    /** @return le nom du lieu */
    public String getNom() { return nom; }

    /** @return l'adresse du lieu */
    public String getAdresse() { return adresse; }

    /** @return la ville du lieu */
    public String getVille() { return ville; }

    /** @return le code postal du lieu */
    public String getCodePostal() { return codePostal; }


    /** @param id définit l'identifiant du lieu */
    public void setId(int id) { this.id = id; }

    /** @param nom définit le nom du lieu */
    public void setNom(String nom) { this.nom = nom; }

    /** @param adresse définit l'adresse du lieu */
    public void setAdresse(String adresse) { this.adresse = adresse; }

    /** @param ville définit la ville du lieu */
    public void setVille(String ville) { this.ville = ville; }

    /** @param codePostal définit le code postal du lieu */
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
}
