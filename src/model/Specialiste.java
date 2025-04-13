package model;

public class Specialiste extends Utilisateur {
    private String qualification;
    private Specialite specialite;

    public Specialiste(int id, String nom, String prenom, String email, String motDePasse,
                       String qualification, Specialite specialite) {
        super(id, nom, prenom, email, motDePasse);
        this.qualification = qualification;
        this.specialite = specialite;
    }

    public String getQualification() { return qualification; }
    public Specialite getSpecialite() { return specialite; }

    public void setQualification(String qualification) { this.qualification = qualification; }
    public void setSpecialite(Specialite specialite) { this.specialite = specialite; }
}