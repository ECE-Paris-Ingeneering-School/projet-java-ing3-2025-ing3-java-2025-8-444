package model;

/* Sources :
https://drive.google.com/file/d/10Qu73GKToaf7oOndfdIq0Zd4hZA8Z7Uc/view
https://drive.google.com/file/d/1QZy3Q5R-kIY697UORIlo7DmL8ZY_Kcwo/view
https://drive.google.com/file/d/1jUEKNnXz6l-_FBw6AGSPawFFzJeSDmTS/view
*/

/**
 * Classe représentant un rendez-vous :
 * Un rendez-vous est associé à un patient, un spécialiste, une disponibilité,
 * un statut et des notes.
 *
 */

public class RendezVous {
    private int id;
    private model.Patient patient;
    private Specialiste specialiste;
    private model.Disponibilite disponibilite;
    private String statut;
    private String notes;

    /**
     * Constructeur principal pour créer un rendez-vous.
     *
     * @param id Identifiant du rendez-vous
     * @param patient Patient associé
     * @param specialiste Spécialiste associé
     * @param disponibilite Disponibilité réservée
     * @param statut Statut du rendez-vous (confirmé, annulé)
     * @param notes Notes sur le rendez-vous
     */

    public RendezVous(int id, model.Patient patient, Specialiste specialiste,
                      model.Disponibilite disponibilite, String statut, String notes) {
        this.id = id;
        this.patient = patient;
        this.specialiste = specialiste;
        this.disponibilite = disponibilite;
        this.statut = statut;
        this.notes = notes;
    }

    /**
     * Récupère le lieu du rendez-vous.
     *
     * @return Le lieu du rendez-vous
     */
    public model.Lieu getLieu() {
        return disponibilite.getLieu();
    }

    /**
     * Retourne l'identifiant du rendez-vous.
     *
     * @return l'identifiant du rendez-vous
     */
    public int getId() { return id; }

    /**
     * Retourne le patient associé au rendez-vous.
     *
     * @return le patient associé au rendez-vous
     */
    public model.Patient getPatient() { return patient; }

    /**
     * Retourne le spécialiste associé au rendez-vous.
     *
     * @return le spécialiste associé au rendez-vous
     */
    public Specialiste getSpecialiste() { return specialiste; }

    /**
     * Retourne la disponibilité liée au rendez-vous.
     *
     * @return la disponibilité liée au rendez-vous
     */
    public model.Disponibilite getDisponibilite() { return disponibilite; }

    /**
     * Retourne le statut du rendez-vous.
     *
     * @return le statut du rendez-vous
     */
    public String getStatut() { return statut; }

    /**
     * Retourne les notes du rendez-vous.
     *
     * @return les notes du rendez-vous
     */
    public String getNotes() { return notes; }

    /**
     * Définit l'identifiant du rendez-vous.
     *
     * @param id l'identifiant du rendez-vous
     */
    public void setId(int id) { this.id = id; }

    /**
     * Définit le patient associé au rendez-vous.
     *
     * @param patient le patient associé au rendez-vous
     */
    public void setPatient(model.Patient patient) { this.patient = patient; }

    /**
     * Définit le spécialiste associé au rendez-vous.
     *
     * @param specialiste le spécialiste associé au rendez-vous
     */
    public void setSpecialiste(Specialiste specialiste) { this.specialiste = specialiste; }

    /**
     * Définit la disponibilité du rendez-vous.
     *
     * @param disponibilite la disponibilité du rendez-vous
     */
    public void setDisponibilite(model.Disponibilite disponibilite) { this.disponibilite = disponibilite; }

    /**
     * Définit le statut du rendez-vous.
     *
     * @param statut le statut du rendez-vous
     */
    public void setStatut(String statut) { this.statut = statut; }

    /**
     * Définit les notes du rendez-vous.
     *
     * @param notes les notes du rendez-vous
     */
    public void setNotes(String notes) { this.notes = notes; }

}
