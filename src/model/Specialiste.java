package model;

/**
 * Classe qui représente un spécialiste :
 * avec une qualification et une spécialité.
 *
 * Cette classe hérite d'Utilisateur
 */

public class Specialiste extends Utilisateur {
    private String qualification;
    private Specialite specialite;

    /**
     * Constructeur de la classe Specialiste.
     *
     * @param id L'identifiant du spécialiste.
     * @param nom Le nom du spécialiste.
     * @param prenom Le prénom du spécialiste.
     * @param email L'email du spécialiste.
     * @param motDePasse Le mot de passe du spécialiste.
     * @param qualification La qualification professionnelle du spécialiste.
     * @param specialite La spécialité du spécialiste.
     */
    public Specialiste(int id, String nom, String prenom, String email, String motDePasse,
                       String qualification, Specialite specialite) {
        super(id, nom, prenom, email, motDePasse);
        this.qualification = qualification;
        this.specialite = specialite;
    }

    /** @return La qualification du specialiste*/
    public String getQualification() { return qualification; }

    /** @return La spécialité du specialite*/
    public Specialite getSpecialite() { return specialite; }


    /**@param qualification La qualification du specialiste*/
    public void setQualification(String qualification) { this.qualification = qualification; }

    /**@param specialite La spécialité du specialiste*/
    public void setSpecialite(Specialite specialite) { this.specialite = specialite; }
}