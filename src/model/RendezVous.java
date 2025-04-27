package model;

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

    /** @return l'identifiant du rendez-vous */
    public int getId() { return id; }

    /** @return le patient associé au rendez-vous */
    public model.Patient getPatient() { return patient; }

    /** @return le spécialiste associé au rendez-vous */
    public Specialiste getSpecialiste() { return specialiste; }

    /** @return la disponibilité liée au rendez-vous */
    public model.Disponibilite getDisponibilite() { return disponibilite; }

    /** @return le statut du rendez-vous */
    public String getStatut() { return statut; }

    /** @return les notes du rendez-vous */
    public String getNotes() { return notes; }


    /** @param id définit l'identifiant du rendez-vous */
    public void setId(int id) { this.id = id; }

    /** @param patient définit le patient associé au rendez-vous */
    public void setPatient(model.Patient patient) { this.patient = patient; }

    /** @param specialiste définit le spécialiste associé au rendez-vous */
    public void setSpecialiste(Specialiste specialiste) { this.specialiste = specialiste; }

    /** @param disponibilite définit la disponibilité du rendez-vous */
    public void setDisponibilite(model.Disponibilite disponibilite) { this.disponibilite = disponibilite; }

    /** @param statut définit le statut du rendez-vous */
    public void setStatut(String statut) { this.statut = statut; }

    /** @param notes définit les notes du rendez-vous */
    public void setNotes(String notes) { this.notes = notes; }
}
