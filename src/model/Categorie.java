package model;

public class Categorie {
    private Integer numero;
    private String nom;
    private Specialiste[] specialiste;

    public Categorie(Integer numero, String nom, Specialiste[] specialiste) {
        this.numero = numero;
        this.nom = nom;
        this.specialiste = specialiste;
    }

    public Categorie() {
    }

    public Integer getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public Specialiste[] getSpecialiste() {
        return specialiste;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSpecialiste(Specialiste[] specialiste) {
        this.specialiste = specialiste;
    }
}
