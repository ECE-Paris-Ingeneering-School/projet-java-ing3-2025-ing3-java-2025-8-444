package model;

public class Specialite {
    private String nom;
    private String description;

    public Specialite(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getNom() { return nom; }
    public String getDescription() { return description; }

    public void setNom(String nom) { this.nom = nom; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return nom;
    }
}